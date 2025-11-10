# K#
K# is a programming language which runs in the JVM. It uses it's own compiler, which converts .ks-files to bytecode. It's usage is similar to your ordinary Java-compiler.

## Usage

To start using the compiler, import the project and open it with your IDE of choice. In the project root, create an executable JAR-file by run the command below:


```text
mvn clean package
```

To execute the JAR, you can run this command directly:

```text
java -jar /path/to/your/KSharp_compiler.jar fileName.ks
```

If you choose to make a bash-script, use this instead:

```text
java -jar /path/to/your/KSharp_compiler.jar "$@"
```

Once you have your bytecode (.class-file), you can run it like you would run any bytecode in the JVM:

```text
java fileName
```


## Syntax:

Below is an example of a program you can paste in your .ks-file:

```text
func main(){
  print welcome//;
  func:number();
  func:summary();
}

func number(){
  num x = 5;
  when (x = 5) {
   print x;
  };
}

func summary(){
  str text = That was a number!;
  print text;
}
```

