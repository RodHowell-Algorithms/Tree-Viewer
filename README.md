# Tree Viewer

**edu.ksu.cis.viewer** is a Java<sup>TM</sup> package of tools for viewing and manipulating trees. The package includes an application that allows the user to add and remove nodes to various types of search trees and to see the results. It also includes classes that can be used within code to display trees. These classes have also been [ported to the Microsoft® .NET Framework](https://github.com/RodHowell-Algorithms/Tree-Viewer-DotNet).

The remainder of this page describes how to install and use the package on your own platform. The Java<sup>TM</sup> SE Runtime Environment (JRE) is required to run the viewer as a stand-alone application, whereas The Java<sup>TM</sup> SE Development Kit (JDK), which includes the JRE, is required to use the class library in your own Java<sup>TM</sup> programs. Both packages can be downloaded from [java.com](https://java.com/en/).

See also the [Release Notes](https://github.com/RodHowell-Algorithms/Tree-Viewer/blob/main/Release%20Notes%202_0.md) for this version.

## Installation

To install the package, simply download the JAR archive [`viewer.jar`](https://github.com/RodHowell-Algorithms/Tree-Viewer/raw/main/viewer.jar). 

## Usage

There are two main uses of this package.

1. **Search Tree Viewer.** This is the stand-alone application for manipulating and visualizing search trees. The program may be started either by opening `viewer.jar` or by executing the following from a command line in the folder containing this archive:

   <pre>
   java -jar viewer.jar
   </pre>
   To create and manipulate a tree, enter any string in the text field in the upper left-hand corner, and press either the Put button to add that string as a key to the tree, or the Remove button to remove that key from the tree. The keys will be maintained in lexicographic order. The Back and Forward buttons allow you to move through the history of your tree construction. The Clone button opens a new window with an exact copy of the tree and history in your current window; the tree in this window can then be manipulated independently.

   For AA Trees and Red Black Trees, red nodes are shown containing red text. Because the colors red and black are indistinguishable for some, monochrome support is provided through the JAR archive [`monochrome-viewer.jar`](https://github.com/RodHowell-Algorithms/Tree-Viewer/raw/main/monochrome-viewer.jar), which can be used in the same way as `viewer.jar`. The only difference in this version is that red nodes are shown containing gray text (using the "Bold" option on the window displaying the trees makes it easier to distinguish these colors). Alternatively, the viewer can be launched in monochrome mode by using the `mono` option when opening `viewer.jar`:
   <pre>
   java -jar viewer.jar mono
   </pre>

   **Important Note:** This app treats all keys as strings. You may enter numbers, but be aware that they will be treated as strings (e.g., "10" < "5"). Virtually all of the questions I've received regarding this program have reflected a misunderstanding of this fact.

2. **Tools for viewing trees built from user code.** In order to be able to implement a tree that can be displayed using this package, you must write your tree class to implement [**TreeInterface**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/TreeInterface.html). If you wish to have colored nodes, you will also need to provide a class that implements [**Colorizer**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/Colorizer.html). You may then use the [**TreeDrawing**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/TreeDrawing.html), [**TreeComponent**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/TreeComponent.html), and/or [**TreeFrame**](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/TreeFrame.html) classes to display your tree. If you didn't add this package to your CLASSPATH, you will need to specify it when you compile and run your program; e.g.:

   <pre>
   javac -classpath viewer.jar *.java
   java -cp .;viewer.jar MyClass.java
   </pre>

   (Depending on your shell, the `;` above may need to be escaped or replaced with a `:`.)

## Compiling the Code

If you wish to modify the code, you will need to download a copy, either by cloning it with `git` or by downloading and decompressing a [ZIP archive](https://github.com/RodHowell-Algorithms/Tree-Viewer/archive/refs/heads/main.zip). To compile the code, assuming you have the [Java Development Kit (JDK)](https://www.java.com/en/download/manual.jsp) installed, enter the following from a command line within the root folder of the project (i.e., the one containing the subfolders, `doc` and `edu`):

<pre>
javac edu/ksu/cis/viewer/*.java
</pre>

(Depending on your shell, you may need to replace each '/' with '\\'.) To run the program after compiling it:

<pre>
java edu.ksu.cis.viewer.Viewer
</pre>
## Documentation

The [complete package documentation](https://rodhowell-algorithms.github.io/Tree-Viewer/doc/edu/ksu/cis/viewer/package-summary.html) can be found in the `doc` folder. To regenerate the documentation using the provided options file, `javadoc-options.txt`:

<pre>
javadoc @javadoc-options.txt
</pre>

## Related Applications

The following programs use the viewer package to render trees:

- [Heap Viewer](https://github.com/RodHowell-Algorithms/Heap-Viewer)
- [Minimum-Sized AVL Tree Viewer](https://github.com/RodHowell-Algorithms/Min-AVL-Trees)
- [Huffman Tree Viewer](https://github.com/RodHowell-Algorithms/Huffman-Tree-Viewer)
- [Suffix Tree Viewer](https://github.com/RodHowell-Algorithms/Suffix-Tree-Viewer)

