# FbHash: A New Similarity Hashing Scheme for Digital Forensics
FbHash (frequency-based hashing) is a similarity hashing tool designed to detect similarity between two digital artifacts.  FbHash computes similarity on the scale of 0 to 100, where 0 indicates no similarity and 100 indicates the 100% similarity or an exact copy.

FbHash works in the following two steps:
		1. Similarity digest generation:  takes a digital artifact as input and generates the similarity digest or fingerprint.
		2. Digest comparison:  takes two similarity digest files as input and generates a similarity score of two fingerprints.

Use the following options to run the tool.
Options:
  -fd [ --file-digest ]           generate digests of a file
  -d  [ --digest ]                generate digests of all files in the directory
  -o  [ --output ]                stores digest to the file
  -c  [ --compare ]               compare two digest files
  -t  [ --threshold ]             show results >= threshold (only works with compare option)
  -v  [ --version ]               print the version information
  -h  [ --help ]                  print help instructions
  
  
  Instructions:
  - to generate digest of a file use following command:
  fbhash -fd "SourcefileName" -o "outputFileName"
  example: fbhash -fd Test-Data\RandomFragment\Text\000504.text -o digest1.text
    
  - to generate digest of all the file in a folder use following command:
  fbhash -d "SourcefolderName" -o "outputFileName"
  example: fbhash -d Test-Data\RandomFragment\Text -o digest1.text
  
  - To compare two digest use following command:
  fbhash -c digest1 digest2
