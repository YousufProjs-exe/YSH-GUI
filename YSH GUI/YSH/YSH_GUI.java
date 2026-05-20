
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class YSH_GUI {

    // file system 
    static class FileNode {
        String name;
        String content = "";

        FileNode(String name) {
            this.name = name;
        }
    }
    // floder system
    static class Folder {
        String name;
        Folder parent;
        ArrayList<Folder> children = new ArrayList<>();
        ArrayList<FileNode> files = new ArrayList<>();

        Folder(String name, Folder parent) {
            this.name = name;
            this.parent = parent;
        }
    }

    // current state
    static Folder root = new Folder("/", null);
    static Folder current = root;

    // gui
    static JTextArea terminal;
    static JTextField input;

    // theme vars
    static Color bgColor = Color.WHITE;
    static Color textColor = Color.BLACK;
    static Color inputColor = Color.BLACK;

    public static void main(String[] args) {

        JFrame frame = new JFrame("YSH GUI");
        frame.setSize(850, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // output area
        terminal = new JTextArea();
        terminal.setFont(new Font("Consolas", Font.PLAIN, 14));
        terminal.setEditable(false);

        JScrollPane scroll = new JScrollPane(terminal);

        // input box
        input = new JTextField();

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(input, BorderLayout.SOUTH);

        // set default theme
        applyTheme();

        // startup text
        printBanner();

        // enter key handler
        input.addActionListener(e -> {
            String cmd = input.getText();
            input.setText("");

            terminal.append("\nysh> " + cmd + "\n");
            execute(cmd);
        });

        frame.setVisible(true);
    }

    // apply colors
    static void applyTheme() {
        terminal.setBackground(bgColor);
        terminal.setForeground(textColor);

        input.setBackground(bgColor);
        input.setForeground(inputColor);
        input.setCaretColor(textColor);
    }

    // startup message
    static void printBanner() {
        terminal.append("YSH Shell GUI\n");
        terminal.append("(C) K.Yousuf 25061-CS-010\n");
        terminal.append("type help for commands\n\n");
    }

    // command handler
    static void execute(String inputText) {

        if (inputText == null) return;

        inputText = inputText.trim();
        if (inputText.isEmpty()) return;

        String command;
        String args;

        if (inputText.contains(" ")) {
            command = inputText.substring(0, inputText.indexOf(" "));
            args = inputText.substring(inputText.indexOf(" ") + 1);
        } else {
            command = inputText;
            args = "";
        }

        switch (command) {

            case "help":
                terminal.append("""
help
echo text
mkdir name
touch name
ls
cd folder / ..
pwd
cat file
write file text
clear
home
exit
theme name
""");
                break;

            case "echo":
                terminal.append(args + "\n");
                break;

            case "pwd":
                terminal.append(getPath(current) + "\n");
                break;

            case "ls":
                for (Folder f : current.children)
                    terminal.append("[DIR] " + f.name + "\n");

                for (FileNode f : current.files)
                    terminal.append("[FILE] " + f.name + "\n");
                break;

            case "mkdir":
                mkdir(args);
                break;

            case "touch":
                touch(args);
                break;

            case "cd":
                cd(args);
                break;

            case "cat":
                cat(args);
                break;

            case "write":
                write(args);
                break;

            case "clear":
                terminal.setText("");
                printBanner();
                break;

            case "home":
                current = root;
                terminal.append("back to root\n");
                break;

            case "exit":
                System.exit(0);
                break;

            case "theme":
                setTheme(args);
                break;

            // easter egg
            case "yousuf":
            case "banana":
            case "816005641":
                terminal.append("01011001 01010011 01001000\n");
                terminal.append("easter egg found\n");
                terminal.append("java is better\n");
                break;

            case "":
                break;

            default:
                terminal.append("command not found\n");
        }
    }

    // path builder
    static String getPath(Folder f) {

        if (f.parent == null) return "/";

        String path = "";
        while (f.parent != null) {
            path = "/" + f.name + path;
            f = f.parent;
        }
        return path;
    }

    // make folder
    static void mkdir(String name) {
        if (name == null || name.isEmpty()) {
            terminal.append("use mkdir name\n");
            return;
        }

        for (Folder f : current.children) {
            if (f.name.equals(name)) {
                terminal.append("folder exists\n");
                return;
            }
        }

        current.children.add(new Folder(name, current));
        terminal.append("folder made\n");
    }

    // make file
    static void touch(String name) {
        if (name == null || name.isEmpty()) {
            terminal.append("use touch name\n");
            return;
        }

        for (FileNode f : current.files) {
            if (f.name.equals(name)) {
                terminal.append("file exists\n");
                return;
            }
        }

        current.files.add(new FileNode(name));
        terminal.append("file made\n");
    }

    // change dir
    static void cd(String name) {

        if (name == null || name.isEmpty()) return;

        if (name.equals("..")) {
            if (current.parent != null)
                current = current.parent;
            return;
        }

        for (Folder f : current.children) {
            if (f.name.equals(name)) {
                current = f;
                return;
            }
        }

        terminal.append("folder not found\n");
    }

    // read file
    static void cat(String name) {

        if (name == null || name.isEmpty()) return;

        for (FileNode f : current.files) {
            if (f.name.equals(name)) {
                terminal.append(f.content + "\n");
                return;
            }
        }

        terminal.append("file not found\n");
    }

    // write file
    static void write(String args) {

        if (args == null || args.isEmpty()) {
            terminal.append("use write file text\n");
            return;
        }

        String[] parts = args.split(" ", 2);

        if (parts.length < 2) {
            terminal.append("use write file text\n");
            return;
        }

        String file = parts[0];
        String text = parts[1];

        for (FileNode f : current.files) {
            if (f.name.equals(file)) {
                f.content = text;
                terminal.append("written\n");
                return;
            }
        }

        terminal.append("file not found\n");
    }

    // theme switch
    static void setTheme(String theme) {

        if (theme == null) return;

        switch (theme.toLowerCase()) {

            case "matrix":
                bgColor = Color.BLACK;
                textColor = Color.GREEN;
                inputColor = Color.GREEN;
                break;

            case "blue":
                bgColor = new Color(10, 10, 40);
                textColor = Color.CYAN;
                inputColor = Color.CYAN;
                break;

            case "purple":
                bgColor = new Color(30, 0, 50);
                textColor = new Color(200, 120, 255);
                inputColor = new Color(200, 120, 255);
                break;

            case "red":
                bgColor = new Color(50, 0, 0);
                textColor = Color.RED;
                inputColor = Color.RED;
                break;

            case "white":
                bgColor = Color.WHITE;
                textColor = Color.BLACK;
                inputColor = Color.BLACK;
                break;

            default:
                terminal.append("themes: matrix blue purple red white\n");
                return;
        }

        applyTheme();
        terminal.append("theme changed\n");
    }
}
