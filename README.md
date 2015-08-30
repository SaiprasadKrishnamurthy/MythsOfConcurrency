### Demystifying Concurrency and understanding the principles of reactive programming

A simple collection of toy problems to understand the fundamentals of Concurrency and the evolution of concurrent programming
techniques on the JVM and demonstration of various abstractions available in the JVM.

I have kept the definitions, terminologies as simple as possible as this is the intent.

Language used is Scala.

## Thread
    A Thread is an entity that represents a line of execution of the code - To keep it simple, this is the one using which our code gets executed.

## Concurrency (vs) Parallelism

To me, this is one of the highly misused word and mixed with each other. The simplest explanation is:

** Concurrency **
    Running one or more tasks one after the other in a quick succession of time, which is too fast for humans to realise that it is actually
    sequential, until all the tasks are finished. Typically, the tasks are executed by one or more threads sharing a single CPU.
    The CPU uses techniques such as timesharing to run these threads one after the other in the succession of microseconds.
    Higher the clock speed of CPU, faster the time it takes to run these threads. Moral of the story is this type of concurrency is still sequential (one after the other).

** Parallelism **
    Running one or more threads simultaneously on multiple cores of the CPU. Yes, really parallel! 
    This is how all the softwares run in our personal computer as of today (assuming that your personal computer has multiple cores in it).

** Why multi cores? **
    We reached the physical limit of our chips as the clock speeds cannot get anymore faster in 2003. This was the climax of Moores Law.
    The chips needed more power for more clock speed, more power means more heat generated and the chips cannot withstand that heat.
    Therefore, our hardware started scaling by introducing more cores.
    
** Challenges in Multicore **
    There's no free meal anymore. Our programs have to scale up and leverage the multiple cores to the fullest extent. Our software should
    inherently utilize all the cores efficiently and this should be welded into the design of the software.
    Writing concurrent software is hard! Debugging them is harder and testing them is hardest!
    
## Evolution..

* Java Thread & Runnable 
* Executors (java.util.concurrent) - Java 5
* Fork Join - Java 7
----------- STILL HARD NEED SIMPLE ABSTRACTIONS! -----------
* Need better abstractions
* Future (Let's discuss Scala futures only as Java future is almost useless.)
* Akka Actors (Scala)
* Rx Java (Java).


