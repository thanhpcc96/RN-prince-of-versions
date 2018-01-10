
package com.reactlibrary;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import co.infinum.princeofversions.LoaderFactory;
import co.infinum.princeofversions.PrinceOfVersions;
import co.infinum.princeofversions.UpdaterResult;
import co.infinum.princeofversions.callbacks.UpdaterCallback;
import co.infinum.princeofversions.loaders.factories.NetworkLoaderFactory;

public class RNPrinceOfVersionsModule extends ReactContextBaseJavaModule {
  private static final String TAG = RNPrinceOfVersionsModule.class.getSimpleName();
  private  static final  String HAD_NEW_VERSION = "HAD_NEW_VERSION";
  private  static final  String NO_NEW_VERSION = "NO_NEW_VERSION";
  private  static final  String HAD_ERROR = "HAD_ERROR";


  private final ReactApplicationContext reactContext;

  PrinceOfVersions updater;
  LoaderFactory loaderFactory;

  public RNPrinceOfVersionsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    this.updater= new PrinceOfVersions(reactContext);

  }
  UpdaterCallback callbackUpdate= new UpdaterCallback() {
    @Override
    public void onNewUpdate(String version, boolean isMandatory, Map<String, String> metadata) {
      newUpdate(version, isMandatory, metadata);
    }

    @Override
    public void onNoUpdate(Map<String, String> metadata) {
        noHadNewVersion(metadata);
    }

    @Override
    public void onError(int error) {
      hadError(error);
    }
  };

  @Nullable
  @Override
  public Map<String, Object> getConstants() {
    final  Map<String, Object> constants= new HashMap<>();
    constants.put(HAD_NEW_VERSION,HAD_NEW_VERSION);
    constants.put(HAD_ERROR, HAD_ERROR);
    constants.put(NO_NEW_VERSION, NO_NEW_VERSION);

    return constants;
  }

  @Override
  public String getName() {
    return "RNPrinceOfVersions";
  }

  @ReactMethod
  public void initURIUpdate(String uri){
    Log.d("init", uri);
    this.loaderFactory= new NetworkLoaderFactory(uri);
  }

  @ReactMethod
  public void checkUpdate(Callback callback){
    Log.d("checkUpdate", "check update");
    UpdaterResult result= updater.checkForUpdates(this.loaderFactory,callbackUpdate);
//    UpdaterResult result= updater.checkForUpdates("http://pastebin.com/raw/QFGjJrLP", callbackUpdate);

  }

  public void newUpdate(String version, boolean isMandatory, Map<String, String> metadata) {
    Log.d("co update", "log uapdate");
    WritableMap params = Arguments.createMap();
    params.putString("version", version);
    params.putBoolean("isMandatory", isMandatory);
   // params.putMap("metadata", (WritableMap) metadata);
   reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(HAD_NEW_VERSION, params);
  }
  public void noHadNewVersion(Map<String, String> metadata){
    Log.d("khong co update", "khong co log uapdate");
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(NO_NEW_VERSION, metadata);
  }
  public void hadError(int error){
    Log.d("co error", "log co error");
    WritableMap params= new Arguments().createMap();
    params.putInt("statusCode",error);
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(HAD_ERROR, params);
  }
}