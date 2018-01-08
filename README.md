# react-native-prince-of-versions

## Getting started

`$ npm install react-native-prince-of-versions --save`

### Mostly automatic installation

`$ react-native link react-native-prince-of-versions`

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-prince-of-versions` and add `RNPrinceOfVersions.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNPrinceOfVersions.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`

* Add `import com.reactlibrary.RNPrinceOfVersionsPackage;` to the imports at the top of the file
* Add `new RNPrinceOfVersionsPackage()` to the list returned by the `getPackages()` method

2. Append the following lines to `android/settings.gradle`:
   ```
   include ':react-native-prince-of-versions'
   project(':react-native-prince-of-versions').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-prince-of-versions/android')
   ```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
   ```
     compile project(':react-native-prince-of-versions')
   ```
4. Insert the following lines inside the dependencies block in
`android/build.gradle`:
   `compile 'co.infinum:prince-of-versions:latest_version'`
   #### Windows
   [Read it! :D](Don't support)

## Usage

```javascript
import RNPrinceOfVersions from "react-native-prince-of-versions";

// TODO: What to do with the module?
RNPrinceOfVersions;
```
