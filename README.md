# YSH-GUI
YSH (Yousuf Shell) is a Java-based custom terminal simulator with a GUI built using Swing. It mimics a basic command-line shell with file system operations, command parsing, themes, and "hidden easter eggs", evolving from a CLI engine into a graphical terminal. The project demonstrates core concepts of OS, file systems, and GUI programming in Java
# YSH - Yousuf Shell (GUI Version)
Overview

YSH is a Java-based custom shell that simulates a terminal inside a GUI window.
It started as a CLI project and evolved into a CMD-style GUI application using Java Swing.

The goal of this project is to understand how terminal systems, command parsing, and file systems work internally.

Features
Command System

Supports basic shell commands:

echo text output
mkdir create folders
touch create files
ls list directory contents
cd navigate folders
pwd show current directory
cat read file content
write write content to file
clear reset terminal screen
home return to root directory
exit close application
theme change UI appearance
File System
Virtual folder structure in memory
File creation and storage
Folder navigation (parent-child system)
File read and write support
Theme System

Built-in UI themes:

white (default clean mode)
matrix (black + green hacker style)
blue (dark blue developer mode)
purple (neon style)
red (warning/error style)

Usage:

theme matrix
Easter Egg System

Hidden commands exist inside the shell:

yousuf
banana
816005641

When triggered, the system enters a hidden mode with a suspense sequence:

fake system log messages
delayed output
hidden binary message
final unlock message

Example behavior:

something feels off...
checking system logs...
01011001 01010011 01001000
accessing hidden layer...
EASTER EGG UNLOCKED
Tech Stack
Java
Swing (GUI)
AWT (UI styling)
Collections framework
How to Run
Compile
javac YSH_GUI.java
Run
java YSH_GUI
Example Usage
mkdir test
cd test
touch file.txt
write file.txt hello world
cat file.txt
ls
pwd
theme matrix
Project Structure
FileNode → represents files
Folder → represents directories
YSH_GUI → main shell engine + GUI
Purpose

This project helps in understanding:

how command line systems work internally
GUI-based command execution
file system simulation
Java Swing applications
command parsing logic
Future Improvements
command history (arrow key support)
autocomplete system (TAB)
real file saving to disk
multi-tab terminal support
plugin-based command system
animated terminal typing effect
Notes

This is a learning project, not a real operating system shell.
It simulates terminal behavior for educational purposes.
