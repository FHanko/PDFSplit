# PDFSplit

A simple command line tool for splitting, merging and zipping pdfs which I need to do frequently. 

    java -jar Split-1.0-all.jar pdf1.pdf 1:9~11,14~22

outputs pages 9 to 11 and 14 to 22 of pdf1.pdf.

    java -jar Split-1.0-all.jar pdf1.pdf 1:22~0

Pages start from 1. 0 is an alias for the last page. 22~0 outputs all pages from 22 to the end of the pdf file which is argument 1.

    java -jar Split-1.0-all.jar pdf1.pdf 1:-3~-4

A Negative number X is an alias for (last page number) + X.

    java -jar Split-1.0-all.jar pdf1.pdf pdf2.pdf 1:1~5 2:1~5

Two or more pdf files can be given as arguments to merge specific page ranges.

    java -jar Split-1.0-all.jar a.pdf b.pdf c.pdf d.pdf e.pdf f.pdf g.pdf 1 2 3 4 5 6 7

A file argument number alone like "1" in the above example is an alias for "1:1~0" which are all the pages of a.pdf.

    java -jar Split-1.0-all.jar a.pdf 1:0~1

Ranges can be reversed. The above command reverses the order of pages in a.pdf.