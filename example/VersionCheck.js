import RNVersion from 'react-native-prince-of-versions';
import { NativeEventEmitter } from 'react-native';

const versionsModule = new NativeEventEmitter(RNVersion);

let instance = null;
export default class Version {
  constructor() {
    if (!instance) {
      instance = this;
      this.registerEvent();
      this.subscriber=null;
    }
    return instance;
  }
  addSubscriber( instance ){
    this.subscriber= instance;
  }
  // register Event
  registerEvent(){
    versionsModule.addListener(RNVersion.HAD_NEW_VERSION,this.handleNewVersion.bind(this));
    versionsModule.addListener(RNVersion.HAD_ERROR,this.handleError.bind(this));
    versionsModule.addListener(RNVersion.NO_NEW_VERSION,this.HandleNoNewVersion.bind(this));
  }

  initURICheck(uri) {
      RNVersion.initURIUpdate(uri)
  }
  checkUpdate(){
      RNVersion.checkUpdate(()=>{
        console.log("check for update");
      })
  }

  // event listenner
  handleNewVersion(data){
    console.log("Had new version", data);
    this.subscriber.showResult("Have a new Version");
  }
  handleError(statusCode){
    console.log("ops! some error, status Code: ", statusCode);
    this.subscriber.showResult("Have a Error");
  }
  HandleNoNewVersion(metadata){
    console.log("Not have new version ", metadata);
    this.subscriber.showResult("NO new version");
  }
}
