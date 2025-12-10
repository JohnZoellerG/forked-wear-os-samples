# Wear OS App Functions Sample

The Wear OS App Functions sample provides a look at the standard App Functions feature set in a
functioning Wear OS app.

## Functionality

An App Functions Wear OS implementation will have the following functionalities:

* Ability the execute defined App Functions on-device 

## Limitations

Wear OS App Functions has the following limitations:

* Does not work with the current on-device Wear OS assistant. So functions can only be invoked via
  adb.  See Testing section, below.
* Must be used on a Wear OS device running Wear 6.1 (Android 16, API 36)

[//]: # (Todo(johnzoeller): Uncomment and add directions once we have this available.)
[//]: # (* Does not work with the current on-device Wear OS assistant. Use your provided copy of the)
[//]: # (  integration testing agent &#40;the AFC app&#41; to perform integration testing. See directions here.)

## Prerequisites

* Android Studio with the latest SDK and build tools
* A Wear OS emulator or physical device on Wear 6.1 or higher.

## Usage

This sample demonstrates the use of App Functions in a Wear OS app.

### Instructions

1. Launch the application on your Wear OS device or emulator.
2. Document visible UI state before invoking any App Function.
3. Open your agent and invoke the 'AddNote' App Function verbally by saying: "Create a note with
   content, 'vocal test'".
4. Reopen the app and verify that a new note is present in the UI.

### Manual Invocation Testing

Use adb to verify that the 'AddNote' App Function can be executed on your device without an agent.

**Invocation Command**
```shell
adb shell cmd app_function execute-app-function \
--package com.example.appfunctions \
--function  com.example.appfunctions.appfunctions.NoteFunctions#AddNote \
--parameters '{"addNoteParams":{"content":"AdbTest"}}'
```
**Expected Output**
```json
{
  "androidAppfunctionsReturnValue": [
    {
      "content": [
        "AdbTest"
      ]
    }
  ]
}
```
**Visual UI Verification**
Open the 'AppFunctions' app on your wear device and verify that you see a new note named 'AdbTest'.

## Support

Stack Overflow: https://stackoverflow.com/questions/tagged/wear-os

If you've found an error in this sample, please file an issue:
https://github.com/android/wear-os-samples

Patches are encouraged, and may be submitted by forking this project and submitting a pull request
through GitHub. Please see CONTRIBUTING.md for more details.