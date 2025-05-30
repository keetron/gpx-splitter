## Splitter
Very simple thing I made to split gpx files with multiple tracks (obtained from gpx.studio) into one file per track.
Reason: Osmand iOS cannot handle a single gpx file with multiple tracks so I have to load these individually if I want to retain file name.

### Usage:
- Make sure the `/target` folder is present in the root of the project
- The source gpx file is present in the root
- Edit the filename of the source data gpx in the source code.
- Run using intellij run command

### Known problems
There can be some metadata and other data present that use the tag `<name>` and the code will create a fresh file for each of those. Right now I remove these manually.

## Renamer
While TomTom _can_ in fact import a single file and split the tracks in routes, otherwise the route organisation in the online app and device can be called poor at best.
With this code, routes can be prefixed with a self-chosen letter code to group them up in the available TomTom interfaces.

### Usage:
- Make sure the `/target` folder is present in the root of the project
- The source gpx file is present in the root
- Edit the filename of the source data gpx in the source code.
- Edit the filename of the target data gpx in the source code.
- Edit the prefix in the source code
- Run using intellij run command

### Known problems
- There can be some metadata and other data present that use the tag `<name>` and the code will happily prefix that. I don't care as I don't use that data.
- Can be slow on larger files, I think the function `File.appendText()` is not optimal and will refetch the same file in full for every line.
