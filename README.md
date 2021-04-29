# FbHash: A New Similarity Hashing Scheme for Digital Forensics
FbHash (frequency-based hashing) is a similarity hashing tool designed to detect similarity between two digital artifacts.  FbHash computes similarity on the scale of 0 to 100, where 0 indicates no similarity and 100 indicates the 100% similarity or an exact copy.<br />

FbHash works in the following two steps:<br />
		1. Similarity digest generation:  takes a digital artifact as input and generates the similarity digest or fingerprint.<br />
		2. Digest comparison:  takes two similarity digest files as input and generates a similarity score of two fingerprints.<br />

Use the following options to run the tool.<br />
Options:<br />
  -fd [ --file-digest ]           generate digests of a file<br />
  -d  [ --digest ]                 generate digests of all files in the directory<br />
  -o  [ --output ]                stores digest to the file<br />
  -c  [ --compare ]              compare two digest files<br />
  -t  [ --threshold ]             show results >= threshold (only works with compare option)<br />
  -v  [ --version ]               print the version information<br />
  -h  [ --help ]                  print help instructions<br />
  
  
  Instructions:<br />
  - to generate digest of a file use following command:<br />
  fbhash -fd "SourcefileName" -o "outputFileName"<br />
  example: fbhash -fd Test-Data\RandomFragment\Text\000504.text -o digest1.text<br />
    
  - to generate digest of all the file in a folder use following command:<br />
  fbhash -d "SourcefolderName" -o "outputFileName"<br />
  example: fbhash -d Test-Data\RandomFragment\Text -o digest1.text<br />
  
  - To compare two digest use following command:<br />
  fbhash -c digest1 digest2<br />
