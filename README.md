# PDFSplit

A simple command line tool for splitting, merging and zipping pdfs which I need to do frequently. 

    java -jar Split-1.0-all.jar pdf1.pdf 1:9~11,14~22

outputs pages 9 to 11 and 14 to 22 of pdf1.

    java -jar Split-1.0-all.jar pdf1.pdf 1:22~0

Pages start from 1. 0 is an alias for the last page. 22~0 outputs all pages from 22 to the end of the pdf file which is argument 1.

    java -jar Split-1.0-all.jar pdf1.pdf 1:-3~-4

A Negative number X is an alias for (last page number) + X.

    java -jar Split-1.0-all.jar pdf1.pdf pdf2.pdf 1:1~5 2:1~5

Two pdf files can be given as arguments to merge specific page ranges.


