
//Title:To develop any distributed application using Message Passing Interface (MPI).
import mpi.MPI;

public class mpj {
    public static void main(String args[]) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int root = 0;
        int sendbuf[] = null;
        sendbuf = new int[size];
        if (rank == root) {
            sendbuf[0] = 10;
            sendbuf[1] = 20;
            sendbuf[2] = 30;
            sendbuf[3] = 40;
            System.out.print("Processor " + rank + " has data: ");
            for (int i = 0; i < size; i++) {
                System.out.print(sendbuf[i] + " ");
            }
            System.out.println();
        }
        int recvbuf[] = new int[1];
        MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);
        System.out.println("Processor " + rank + " has data: " + recvbuf[0]);
        System.out.println("Processor " + rank + " is doubling the data");
        recvbuf[0] = recvbuf[0] * 2;
        MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);
        if (rank == root) {
            System.out.println("Process 0 has data: ");
            for (int i = 0; i < 4; i++) {
                System.out.print(sendbuf[i] + " ");
            }
        }
        MPI.Finalize();
    }
}
// ********************** Output ********************
/*
 * gurukul@gurukul-ThinkCentre-M57e:~$ export
 * MPJ_HOME=/home/gurukul/Desktop/MPI/mpj-v0_44
 * gurukul@gurukul-ThinkCentre-M57e:~$ cd Desktop
 * gurukul@gurukul-ThinkCentre-M57e:~/Desktop$ cd MPI
 * gurukul@gurukul-ThinkCentre-M57e:~/Desktop/MPI$ javac -cp
 * $MPJ_HOME/lib/mpj.jar mpj.java
 * gurukul@gurukul-ThinkCentre-M57e:~/Desktop/MPI$ $MPJ_HOME/bin/mpjrun.sh -np 4
 * mpj
 * MPJ Express (0.44) is started in the multicore configuration
 * Processor 0 has data: 10 20 30 40
 * Processor 0 has data: 10
 * Processor 0 is doubling the data
 * Processor 1 has data: 20
 * Processor 1 is doubling the data
 * Processor 2 has data: 30
 * Processor 2 is doubling the data
 * Processor 3 has data: 40
 * Processor 3 is doubling the data
 * Process 0 has data:
 * 20 40 60 80
 * gurukul@gurukul-ThinkCentre-M57e:~/Desktop/MPI$
 */