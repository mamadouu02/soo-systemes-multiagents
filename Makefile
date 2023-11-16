# Example de makefile pour compiler le squelette de code distribué
# Vous pouvez compléter ce makefile, mais étant donnée la taille du projet, 
# il est FORTEMENT recommandé d'utiliser un IDE!

# Organisation:
#  1) Les sources (*.java) se trouvent dans le répertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) sont générés dans le répertoire bin
#     La hiérarchie des sources (par package) est conservée.
#
#  3) Une librairie gui.jar est distribuée pour l'interface grapique. 
#     Elle se trouve dans le sous-répertoire lib.
#
# Compilation:
#  Options de javac:
#   -d : répertoire dans lequel sont générés les .class compilés
#   -sourcepath : répertoire dans lequel sont cherchés les .java
#   -classpath : répertoire dans lequel sont cherchées les classes compilées (.class et .jar)

JC = javac
DOCC = javadoc
LATEXC = pdflatex
PROG ?= Invader

BINDIR = bin
DOCDIR = doc
LIBDIR = dir
SRCDIR = src

all: Test$(PROG)

compile:
	$(JC) -d $(BINDIR) -classpath $(LIBDIR)/gui.jar src/Test$(PROG).java

run: compile
	java -classpath $(BINDIR):$(LIBDIR)/gui.jar Test$(PROG)

doc:
	$(DOCC) $(SRCDIR) -d $(DOCDIR)

clean:
	rm -rf $(BINDIR)/
