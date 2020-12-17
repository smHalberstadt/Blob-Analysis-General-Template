# Blob-Analysis-General-Template (Java)
__NOTE: This is tailored for "The Atomic Nature of Matter" as a general case, but I do not do the mathematics. This is just a reference for future blob detection code for Java.__
This general use blob detection Java project outlines a strategy for one type of pattern detection using pictures.
For testing I am using Princeton's Project : https://www.cs.princeton.edu/courses/archive/spr15/cos126/assignments/atomic.html
I am also Using Eclipse 2020-09 to run this code
- - - -
## Image Analysis
Image analysis in this case is identifying circular/oval groups of pixels.
This algorithm should be somewhat inefficient for blobs over something like 
100 pixels, but since I do not expect for this specific program.
Essentially, it branches out and touches every pixel until it either;
1. hits the boundaries of the picture
2. does not hit a pixel that could be used for a blob
I don't go down since the blobs are circular/oval shaped and make it slightly
more efficient. For other projects this would need to be changed.
- - - -
### Version Notes (mm/dd/yy)
 - (12/13/20) V1
started project, made outline code for:
Blob, Blob_Finder
 - (12/14/20)
made outline code for:
Image_Processor, Blob_Tracker
 - (12/15/20)
worked on:
Blob, Blob_Finder,Image_Processor, Blob_Tracker
 - (12/16/20)-(12/17/20)
worked on:
Blob_Tracker, blob_main
