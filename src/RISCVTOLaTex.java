import java.io.*;
import java.util.*;

public class RISCVTOLaTex {

    // List of all instructions
    public static final String[] INSTRUCTIONS = {
            "lb", "lh", "lw", "lbu", "lhu", "sb", "sh",
            "sw", "sll", "slli", "srl", "srli", "sra",
            "srai", "add", "addi", "sub", "lui", "auipc",
            "xor", "xori", "or", "ori", "and", "andi",
            "slt", "slti", "sltu", "sltiu", "beq", "bne",
            "blt", "bge", "bltu", "bgeu", "j", "jr", "jal",
            "jalr", "ret", "scall", "break", "nop", "rem",
            "remu", "div", "divu", "mul", "mulh", "mulhu",
            "mulhsu", "li", "mv", "la", "neg", "ret",
            "seqz"};

    // List of all registers
    public static final String[] REGISTERS = {
            "zero", "ra", "sp", "gp", "tp", "s0", "fp",
            "t0", "t1", "t2", "t3", "t4", "t5", "t6",
            "s1", "s2", "s3", "s4", "s5", "s6", "s7",
            "s8", "s9", "s10", "s11", "a0", "a1", "a2",
            "a3", "a4", "a5", "a6", "a7", "ft0", "ft1",
            "ft2", "ft3", "ft4", "ft5", "ft6", "ft7",
            "fs0", "fs1", "fs2", "fs3", "fs4", "fs5",
            "fs6", "fs7", "fs8", "fs9", "fs10", "fs11",
            "fa0", "fa1", "fa2", "fa3", "fa4", "fa5",
            "fa6", "fa7", "x0", "x1", "x2", "x3", "x4",
            "x5", "x6", "x7", "x8", "x9", "x10", "x11",
            "x12", "x13", "x14", "x15", "x16", "x17",
            "x18", "x19", "x20", "x21", "x22", "x23",
            "x24", "x25", "x26", "x27", "x28", "x29",
            "x30", "x31"};

    // List of all keywords
    public static final String[] KEYWORDS = {
            ".align", ".ascii", ".asciiz", ".byte",
            ".data", ".double", ".extern", ".float",
            ".globl", ".half", ".kdata", ".ktext",
            ".set", ".space", ".text", ".word"};

    // Colors for different types
    public static final String[] INSTRUCTIONS_COLOR = {"200", "0", "0"}; // Set colors for instructions
    public static final String[] REGISTERS_COLOR = {"40", "40", "255"}; // Set colors for registers
    public static final String[] KEYWORDS_COLOR = {"204", "0", "153"}; // Set colors for keywords
    public static final String[] COMMENTS_COLOR = {"153", "153", "153"}; // Set colors for comments
    public static final String[] DECIMALS_COLOR = {"0", "153", "0"}; // Set colors for decimal
    public static final String[] HEXADECIMALS_COLOR = {"0", "153", "0"}; // Set colors for hexadecimal
    public static final String[] BINARIES_COLOR = {"0", "153", "0"}; // Set colors for binaries
    public static final String[] WORDS_COLOR = {"77", "0", "77"}; // Set colors for words

    public static void main(String[] args) throws Exception {
        File fIn = new File("assembly.txt");
        File fOut = new File("output.txt");
        FileWriter fw = new FileWriter(fOut);
        PrintWriter pw = new PrintWriter(fw);

        // Create color file
        File fColors = new File("colors.txt");
        FileWriter fw2 = new FileWriter(fColors);
        PrintWriter pw2 = new PrintWriter(fw2);
        pw2.println("\\usepackage[dvipsnames]{xcolor}\n");
        pw2.println("\\definecolor{INSTRUCTIONS_COLOR}{RGB}{" + INSTRUCTIONS_COLOR[0] + "," + INSTRUCTIONS_COLOR[1] + "," + INSTRUCTIONS_COLOR[2] + "}");
        pw2.println("\\definecolor{REGISTERS_COLOR}{RGB}{" + REGISTERS_COLOR[0] + "," + REGISTERS_COLOR[1] + "," + REGISTERS_COLOR[2] + "}");
        pw2.println("\\definecolor{KEYWORDS_COLOR}{RGB}{" + KEYWORDS_COLOR[0] + "," + KEYWORDS_COLOR[1] + "," + KEYWORDS_COLOR[2] + "}");
        pw2.println("\\definecolor{COMMENTS_COLOR}{RGB}{" + COMMENTS_COLOR[0] + "," + COMMENTS_COLOR[1] + "," + COMMENTS_COLOR[2] + "}");
        pw2.println("\\definecolor{DECIMALS_COLOR}{RGB}{" + DECIMALS_COLOR[0] + "," + DECIMALS_COLOR[1] + "," + DECIMALS_COLOR[2] + "}");
        pw2.println("\\definecolor{HEXADECIMALS_COLOR}{RGB}{" + HEXADECIMALS_COLOR[0] + "," + HEXADECIMALS_COLOR[1] + "," + HEXADECIMALS_COLOR[2] + "}");
        pw2.println("\\definecolor{BINARIES_COLOR}{RGB}{" + BINARIES_COLOR[0] + "," + BINARIES_COLOR[1] + "," + BINARIES_COLOR[2] + "}");
        pw2.println("\\definecolor{WORDS_COLOR}{RGB}{" + WORDS_COLOR[0] + "," + WORDS_COLOR[1] + "," + WORDS_COLOR[2] + "}");
        pw2.close();

        if (!fIn.exists()) {
            throw new Exception("File does not exist.");
        } else {
            Scanner fScan = new Scanner(fIn);
            String s = "";
            pw.println("\\ttfamily");
            String t = "";
            while (fScan.hasNextLine()){
                s = fScan.nextLine();
                Scanner sScan = new Scanner(s);
                while (sScan.hasNext()) {
                    t = sScan.next();
                    boolean found = false;
                    for (int i = 0; i < INSTRUCTIONS.length; i++){
                        if (t.contains(INSTRUCTIONS[i])){
                            pw.print("\\textcolor{INSTRUCTIONS_COLOR}{" + t + "}\t");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        for (int i = 0; i < REGISTERS.length; i++) {
                            if (t.contains(REGISTERS[i])) {
                                pw.print("\\textcolor{REGISTERS_COLOR}{" + t + "}\t");
                                found = true;
                                break;
                            }
                        }
                    }

                    if (!found) {
                        for (int i = 0; i < KEYWORDS.length; i++) {
                            if (t.contains(KEYWORDS[i])) {
                                pw.print("\\textcolor{KEYWORDS_COLOR}{" + t + "}\t");
                                found = true;
                                break;
                            }
                        }
                    }

                    if (!found) {
                        if (t.contains("0x")){
                            pw.print("\\textcolor{HEXADECIMALS_COLOR}{" + t + "}\t");
                            found = true;
                        }
                    }

                    if (!found) {
                        if (t.contains("0b")){
                            pw.print("\\textcolor{BINARIES_COLOR}{" + t + "}\t");
                            found = true;
                        }
                    }

                    if (!found) {
                        if (t.matches(".*\\d.*")){
                            pw.print("\\textcolor{DECIMALS_COLOR}{" + t + "}\t");
                            found = true;
                        }
                    }

                    if (!found) {
                        if (!t.contains("#")){
                            pw.print("\\textcolor{WORDS_COLOR}{" + t + "}\t");
                            found = true;
                        }
                    }

                    if (!found) {
                        while (sScan.hasNext()){
                            t += " " + sScan.next();
                        }
                        t = t.replace("#", "\\#");
                        pw.print("\\textcolor{COMMENTS_COLOR}{" + t + "}\t");
                    }
                }
                sScan.close();
                pw.println("\\\\");
            }
            fScan.close();
        }
        pw.println("\\rmfamily");
        pw.close();
        System.out.println("Conversion done. Output is placed i output.txt");
    }
}
