# RISC-V to LaTeX
Converts RISC-V assembly to LaTeX. I needed to insert a lot of assembly into an assignment. I wanted the assembly to be highlighted so it is nicer to read. I thus created this simple java program that creates highlighted assembly.

To use it just put your assembly into the ```assembly.txt``` and run the program. The program will generate highlighted assembly in LaTeX in the ```output.txt``` file.

To use the text in ```output.txt``` copy the contents of ```colors.txt``` into your main document, this contains the package info and color definitions. It is possible to change the colors of the highlighted assembly by changing the RGB values in the file.
