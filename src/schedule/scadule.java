package schedule;

import java.util.Scanner;

public class scadule {


        Scanner sc = new Scanner(System.in);
        int[] burstTime, rem, waiting, turnaround, AT, PRI, QUA, P;
        int size, q, AVGW = 0, AVGT = 0, flag = 0, time, remain;

        scadule(int size) {
            this.size = size;
            burstTime = new int[size];
            waiting = new int[size];
            turnaround = new int[size];
            rem = new int[size];
            AT = new int[size];
            PRI = new int[size];
            P = new int[size];

        }

        void ProcessesPRI() {
            for (int i = 0; i < size; i++) {
                System.out.print("Enter burst time of P" + (i + 1) + ":");
                burstTime[i] = rem[i] = sc.nextInt();
                System.out.print("Enter Arrival time of P" + (i + 1) + ":");
                AT[i] = sc.nextInt();

            }



        }
        void Processes() {
            for (int i = 0; i < size; i++) {
                System.out.print("Enter burst time of P" + (i + 1) + ":");
                burstTime[i] = rem[i] = sc.nextInt();
                System.out.print("Enter Arrival time of P" + (i + 1) + ":");
                AT[i] = sc.nextInt();

            }

            System.out.println("Enter the quantum time : ");
            q = sc.nextInt();

        }




        void round() {

            while (true) {
                boolean done = true;
                for (int i = 0; i < size; i++) {
                    if (rem[i] > 0) {
                        done = false;
                        System.out.print("P" + (i + 1) + "\t");
                        if (rem[i] > q) {
                            flag += q; // how much time a process has been proccesed
                            rem[i] -= q;
                        } else {
                            System.out.print("P" + (i + 1) + "\t");
                            flag = flag + rem[i];
                            waiting[i] = flag - burstTime[i];
                            rem[i] = 0;

                        }
                    }

                }

                for (int i = 0; i < size; i++) {
                    turnaround[i] = waiting[i] + burstTime[i];
                }

                if (done == true) {
                    break;
                }

            }
        }

        void Priority() {
            int pos = 0, temp;
            Scanner s = new Scanner(System.in);

            System.out.println("Enter the priority time");
            for (int i = 0; i < size; i++) {
                PRI[i] = sc.nextInt();
                P[i] = i + 1;
            }
            for (int i = 0; i < size; i++) {
                pos = i;
                for (int j = i + 1; j < size; j++) {
                    if (PRI[j] < PRI[pos]) {
                        pos = j;
                    }
                }
                temp = PRI[pos];
                PRI[pos] = PRI[i];
                PRI[i] = temp;

                temp = burstTime[pos];
                burstTime[pos] = burstTime[i];
                burstTime[i] = temp;

                temp = P[i];
                P[i] = P[pos];
                P[pos] = temp;

            }
            waiting[0] = 0;
            for (int i = 1; i < size; i++) {
                // waiting[i] = 0;
                for (int j = 0; j < i; j++) {
                    waiting[i] = waiting[i - 1] + burstTime[j];
                }

            }
            for (int i = 0; i < size; i++) {
                turnaround[i] = waiting[i] + burstTime[i];

                //  System.out.println("p" + (i + 1) + "\t" + burstTime[i] + "\t" + PRI[i] + "\t\t" + waiting[i] + "\t\t" + turnaround[i]);
            }
        }



        void ShowPriority() {

            System.out.println("\nProcess \tBurstTime \t Priority \t Waiting\t Turnaround \t ");
            for (int i = 0; i < size; i++) {
                //System.out.println("P" + (i + 1) + "\t" + waiting[i] + "\t" + turnaround[i] + "\t" + burstTime[i] );
                System.out.println("p" + P[i] + "\t\t" + burstTime[i] + "\t\t" + PRI[i] + "\t\t" + waiting[i] + "\t\t" + turnaround[i]);
                AVGW = AVGW + waiting[i];
                AVGT += turnaround[i];
            }
            System.out.println(" Waiting time: " + AVGW + "  The Avg waiting time: " + ((AVGW * 1.0) / size));
            System.out.println(" Turnaround time: " + AVGT + "The avg turnaround time: " + ((AVGT * 1.0) / size));

        }

        void Show() {

            System.out.println("\nProcess\tBurstTime\tWaiting\tTurnaround\tReminder ");
            for (int i = 0; i < size; i++) {
                //System.out.println("P" + (i + 1) + "\t" + waiting[i] + "\t" + turnaround[i] + "\t" + burstTime[i] );
                System.out.println("p" + (i + 1) + "\t" + burstTime[i] + "\t" + waiting[i] + "\t" + turnaround[i] + "\t" + rem[i]);
                AVGW += waiting[i];
                AVGT += turnaround[i];
            }
            System.out.println("Average waiting time:" + (AVGW * 1.0 / size));
            System.out.println("Average Turnaround time:" + (AVGT * 1.0 / size));

        }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes:");
        int n = s.nextInt();
        scadule obj = new scadule(n);
        System.out.println("if you want piriority enter 1");
        System.out.println("if you want RR enter 2");
        int x=s.nextInt();
        if(x==1){
            obj.ProcessesPRI();
            obj.Priority();
            obj.ShowPriority();

        }
        if(x==2){
            obj.Processes();
            obj.round();
            obj.Show();
        }
    }

    }

