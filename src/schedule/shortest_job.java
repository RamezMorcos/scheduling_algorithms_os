package schedule;

import java.util.ArrayList;
import java.util.Scanner;

public class shortest_job {
    public void sorting(ArrayList<process> s) {
        process temp = new process();
        for (int i = 1; i < s.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (s.get(j).arrival < s.get(j - 1).arrival) {
                    temp.arrival = s.get(j).arrival;
                    temp.burst = s.get(j).burst;
                    temp.name = s.get(j).name;
                    s.get(j).arrival = s.get(j - 1).arrival;
                    s.get(j).burst = s.get(j - 1).burst;
                    s.get(j).name = s.get(j - 1).name;
                    s.get(j - 1).arrival = temp.arrival;
                    s.get(j - 1).burst = temp.burst;
                    s.get(j - 1).name = temp.name;
                }
            }
        }
    }
int count=0;
    public int index(ArrayList<process> s, int n) {
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (s.get(i).finished == true) {
                pos = i;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (s.get(j).burst < s.get(pos).burst && (s.get(j).finished == true)) {
                pos = j;

            }
        }
        return pos;
    }
    public int index_1(ArrayList<process> s, int n,int a) {
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if(a==i){
                continue;
            }
            else if (s.get(i).finished == true) {
                pos = i;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if(a==j){
                continue;
            }
            if (s.get(j).burst < s.get(pos).burst && (s.get(j).finished == true)) {
                pos = j;

            }
        }
        return pos;
    }
    public void sjf(ArrayList<process> p) {
        sorting(p);
        ArrayList<process> queue = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            p.get(i).arrival_time_1 = p.get(i).arrival;
            p.get(i).burst_time_1=p.get(i).burst;
        }

        for (int i = 0; i < p.size(); i++) {
            if (i == 0) {

                p.get(i).arrival = p.get(i + 1).arrival - p.get(i).arrival;
                p.get(i).burst = p.get(i).burst - p.get(i).arrival;
                count+=p.get(i).arrival;
                process pr = new process();
                if (p.get(i).burst <= 0) {
                    p.get(i).finished = false;
                    p.get(i).arrival = p.get(i).arrival + p.get(i).burst;
                    pr.turnaround_time = p.get(i).turnaround_time;
                    count=p.get(i).arrival;
                    p.get(i).burst = 0;
                }

                pr.name = p.get(i).name;
                pr.arrival = p.get(i).arrival;
                pr.burst = p.get(i).burst;
                pr.finished = p.get(i).finished;
                queue.add(pr);
            } else {


                int pos = index(p, i);
//
                if (p.get(i).burst < p.get(pos).burst&&count>=p.get(i).arrival) {
                    if (i == p.size() - 1) {

                        p.get(i).finished = false;
                        process pr = new process();
                        int size = (queue.size() - 1);
                        pr.name = p.get(i).name;
                        pr.arrival = p.get(i).burst + queue.get(size).arrival;
                        count+=pr.arrival;
                        pr.turnaround_time = pr.arrival;
                        p.get(i).burst = 0;
                        pr.burst = p.get(i).burst;
                        pr.finished = p.get(i).finished;
                        queue.add(pr);
                    } else {
                        p.get(i).arrival = p.get(i + 1).arrival - p.get(i).arrival;
                        p.get(i).burst = p.get(i).burst - p.get(i).arrival;
                        process pr = new process();
                        if (p.get(i).burst <= 0) {
                            p.get(i).finished = false;
                            p.get(i).arrival = p.get(i).arrival + p.get(i).burst;
                            count+=p.get(i).arrival;
                            p.get(i).burst = 0;
                        }
                        pr.name = p.get(i).name;
                        int size = (queue.size() - 1);
                        pr.arrival = p.get(i).arrival + queue.get(size).arrival;
                        if (p.get(i).burst == 0) {
                            pr.turnaround_time = pr.arrival;
                        }
                        pr.burst = p.get(i).burst;
                        pr.finished = p.get(i).finished;
                        queue.add(pr);
                    }
                } else {

                    p.get(pos).finished = false;
                    process pr = new process();
                    int size = (queue.size() - 1);
                    pr.name = p.get(pos).name;
                    pr.arrival = p.get(pos).burst + queue.get(size).arrival;
                    count+=pr.arrival;
                    pr.turnaround_time = pr.arrival;
                    p.get(pos).burst = 0;
                    pr.burst = p.get(pos).burst;
                    pr.finished = p.get(pos).finished;
                    queue.add(pr);
                }
            }

        }
        int pos = 0;


        for (int i = 0; i <= p.size(); i++) {
            pos = index(p, p.size());
            if (p.get(pos).finished != false) {
                p.get(pos).finished = false;
                process pr = new process();
                pr.name = p.get(pos).name;
                int size = (queue.size() - 1);
                pr.arrival = p.get(pos).burst + queue.get(size).arrival;
                pr.turnaround_time = pr.arrival;
                p.get(pos).burst = 0;
                pr.burst = p.get(pos).burst;
                pr.finished = p.get(pos).finished;
                queue.add(pr);
            }
        }
        for (int i = 0; i < queue.size(); i++) {
            for (int j = 0; j < p.size(); j++) {
                if (queue.get(i).name.equals(p.get(j).name)) {
                    queue.get(i).arrival_time_1 = p.get(j).arrival_time_1;
                    queue.get(i).burst_time_1=p.get(j).burst_time_1;
                }
            }


        }



        System.out.println("0");

        for (int i = 0; i < queue.size(); i++) {
            System.out.println(queue.get(i).name);
            System.out.println(queue.get(i).arrival);
        }
        double aver_turn_arround = 0;
        double aver_waiting_time = 0;
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).turnaround_time != 0) {
                queue.get(i).turnaround_time=queue.get(i).turnaround_time-queue.get(i).arrival_time_1;
                queue.get(i).waiting_time=queue.get(i).turnaround_time-queue.get(i).burst_time_1;
                System.out.println(queue.get(i).name);
                System.out.println("the turnarround for the process is ");
                System.out.println(queue.get(i).turnaround_time);
                System.out.println("the waiting time for the process is ");
                aver_turn_arround += queue.get(i).turnaround_time;
                aver_waiting_time += queue.get(i).waiting_time;
                System.out.println(queue.get(i).waiting_time);

            }
        }
        System.out.println("average turnarround is");
        aver_turn_arround = aver_turn_arround / p.size();
        System.out.println(aver_turn_arround);
        System.out.println("average waiting time is");
        aver_turn_arround = aver_waiting_time / p.size();
        System.out.println(aver_waiting_time);

      /*  ArrayList<process> waiting_time = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            process pr = new process();
            pr.name = p.get(i).name;
            pr.arrival_time_1 = p.get(i).arrival_time_1;
            waiting_time.add(pr);
        }
        for (int i = 0; i < waiting_time.size(); i++) {
            int counter = 0;
            int waiting = 0;
            int s = 0;
            for (int j = 0; j < queue.size(); j++) {

                if (waiting_time.get(i).name.equals(queue.get(j).name)) {

                    if (counter == 0 && j != 0) {
                        waiting = queue.get(j - 1).arrival - queue.get(j).arrival_time_1;
                        s = queue.get(j).arrival;
                        counter++;

                    } else if (j == 0) {
                        counter++;
                        s = queue.get(j).arrival;
                    } else {
                        waiting = waiting + queue.get(j - 1).arrival - s;
                        s = queue.get(j).arrival;
                    }
                }
            }
            waiting_time.get(i).waiting_time = waiting;
        }
        double aver_waiting_time = 0;
        for (int i = 0; i < waiting_time.size(); i++) {
            // System.out.println(waiting_time.get(i).arrival_time_1);
            System.out.println(waiting_time.get(i).name);
            System.out.println(waiting_time.get(i).waiting_time);
            aver_waiting_time += waiting_time.get(i).waiting_time;
        }
        System.out.println("avergae waiting time is");
        aver_waiting_time = aver_waiting_time / waiting_time.size();
System.out.println(aver_waiting_time);
*/
    }

    public void AG(ArrayList<process> p) {
        sorting(p);
        ArrayList<process> queue = new ArrayList<>();
        for (int i = 0; i < p.size(); i++) {
            p.get(i).arrival_time_1 = p.get(i).arrival;
            p.get(i).burst_time_1=p.get(i).burst;
            p.get(i).time_quantum_1 = p.get(i).time_quantum;
        }
        boolean x = true;

        int counter = 0;
        for (int i = 0; i < p.size(); i++) {
            if (counter >= p.get(i).arrival) {
                if (i == 0) {
                    if (p.get(i).time_quantum % 2 == 0) {
                        p.get(i).time_quantum = p.get(i).time_quantum / 2;
                        p.get(i).rest_time_q = p.get(i).time_quantum;
                       /* System.out.println(p.get(i).name);
                        System.out.println(p.get(i).time_quantum_1);
                        System.out.println("after ceil");
                        System.out.println(p.get(i).time_quantum);*/


                    } else {
                        p.get(i).time_quantum = p.get(i).time_quantum / 2 + 1;
                        p.get(i).rest_time_q = p.get(i).time_quantum_1 - p.get(i).time_quantum;
                        System.out.println(p.get(i).name);
                        System.out.println(p.get(i).time_quantum_1);
                        System.out.println("after ceil");
                        System.out.println(p.get(i).time_quantum);

                    }
                    process pr = new process();
                    p.get(i).burst = p.get(i).burst - p.get(i).time_quantum;
                    counter += p.get(i).time_quantum;
                    if (p.get(i).burst <= 0) {
                        while (p.get(i).burst < 0) {
                            p.get(i).time_quantum--;
                            p.get(i).burst++;
                        }
                        System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                        p.get(i).burst = 0;
                        p.get(i).time_quantum = 0;
                        p.get(i).arrival = p.get(i).time_quantum;
                        pr.turnaround_time = p.get(i).arrival;
                        p.get(i).finished = false;
                        counter = p.get(i).time_quantum;
                    } else if (p.get(i + 1).arrival > p.get(i).time_quantum) {
                        p.get(i).burst = p.get(i).burst - p.get(i).rest_time_q;
                        if (p.get(i).burst <= 0) {
                            while (p.get(i).burst < 0) {
                                p.get(i).time_quantum_1--;
                                p.get(i).burst++;
                            }
                            System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                            p.get(i).finished = false;
                            p.get(i).arrival = p.get(i).time_quantum_1;
                            counter += p.get(i).arrival;
                            pr.turnaround_time = p.get(i).arrival;
                        } else if (p.get(i).burst > 0) {
                            p.get(i).arrival = p.get(i).time_quantum_1;
                            p.get(i).time_quantum_1 += 1;
                            p.get(i).time_quantum = p.get(i).time_quantum_1;

                        }

                    } else {
                        p.get(i).arrival = p.get(i).time_quantum;
                        p.get(i).time_quantum = p.get(i).time_quantum_1 + p.get(i).rest_time_q;
                        p.get(i).time_quantum_1 = p.get(i).time_quantum;
                    }
                    pr.name = p.get(i).name;
                    pr.burst = p.get(i).burst;
                    pr.arrival = p.get(i).arrival;
                    pr.time_quantum_1 = p.get(i).time_quantum_1;
                    pr.time_quantum = p.get(i).time_quantum;
                    pr.rest_time_q = p.get(i).rest_time_q;
                    pr.finished = p.get(i).finished;
                    pr.burst_time_1=p.get(i).burst_time_1;
                    pr.waiting_time=p.get(i).burst_time_1;
                    pr.arrival_time_1 = p.get(i).arrival_time_1;
                    queue.add(pr);

                }
                else {
                    int pos = 0;
                    pos = index(p, i);
                    if (p.get(i).burst < p.get(pos).burst) {
                        if (p.get(i).time_quantum % 2 == 0) {
                            p.get(i).time_quantum = p.get(i).time_quantum / 2;
                            p.get(i).rest_time_q = p.get(i).time_quantum;
                            System.out.println(p.get(i).name);
                            System.out.println(p.get(i).time_quantum_1);
                            System.out.println("after ceil");
                            System.out.println(p.get(i).time_quantum);
                        } else {
                            p.get(i).time_quantum = p.get(i).time_quantum / 2 + 1;
                            p.get(i).rest_time_q = p.get(i).time_quantum_1 - p.get(i).time_quantum;
                            System.out.println(p.get(i).name);
                            System.out.println(p.get(i).time_quantum_1);
                            System.out.println("after ceil");
                            System.out.println(p.get(i).time_quantum);
                        }
                        process pr = new process();
                        p.get(i).burst = p.get(i).burst - p.get(i).time_quantum;
                        if (p.get(i).burst <= 0) {
                            while (p.get(i).burst < 0) {
                                p.get(i).time_quantum--;
                                p.get(i).burst++;
                            }
                            System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                            p.get(i).burst = 0;
                            p.get(i).time_quantum = 0;
                            p.get(i).arrival = p.get(i).time_quantum;
                            counter += p.get(i).arrival;
                            pr.turnaround_time = p.get(i).arrival;
                            p.get(i).finished = false;
                        }
                        pos = index(p, i);
                        if ((i < p.size() - 1 && p.get(i + 1).arrival > counter) || (pos == i )) {
                            p.get(i).burst = p.get(i).burst - p.get(i).rest_time_q;
                            if (p.get(i).burst <= 0) {
                                while (p.get(i).burst < 0) {
                                    p.get(i).time_quantum_1--;
                                    p.get(i).burst++;
                                }
                                System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                                p.get(i).finished = false;
                                p.get(i).arrival = p.get(i).time_quantum_1;
                                counter += p.get(i).arrival;
                                pr.turnaround_time = p.get(i).arrival;
                                p.get(i).time_quantum=0;
                                p.get(i).time_quantum_1=0;
                            } else if (p.get(i).burst > 0) {
                                p.get(i).arrival = p.get(i).time_quantum_1;
                                p.get(i).time_quantum_1 += 1;
                                p.get(i).time_quantum = p.get(i).time_quantum_1;

                            }

                        } else {
                            counter += p.get(i).time_quantum;
                            p.get(i).arrival = p.get(i).time_quantum;
                        }
                        pr.burst = p.get(i).burst;
                        int size = queue.size();
                        pr.name = p.get(i).name;
                        pr.arrival = queue.get(size - 1).arrival + p.get(i).arrival;
                        pr.time_quantum_1 = p.get(i).time_quantum_1;
                        pr.time_quantum = p.get(i).time_quantum;
                        pr.rest_time_q = p.get(i).rest_time_q;
                        pr.finished = p.get(i).finished;
                        pr.burst_time_1=p.get(i).burst_time_1;
                        pr.arrival_time_1 = p.get(i).arrival_time_1;
                        queue.add(pr);
                    } else if (p.get(pos).burst < p.get(i).burst) {
                        if (p.get(pos).time_quantum % 2 == 0) {
                            p.get(pos).time_quantum = p.get(pos).time_quantum / 2;
                            p.get(pos).rest_time_q = p.get(pos).time_quantum;
                            System.out.println(p.get(i).name);
                            System.out.println(p.get(i).time_quantum_1);
                            System.out.println("after ceil");
                            System.out.println(p.get(i).time_quantum);
                        } else {
                            p.get(pos).time_quantum = p.get(pos).time_quantum / 2 + 1;
                            p.get(pos).rest_time_q = p.get(pos).time_quantum_1 - p.get(pos).time_quantum;
                            System.out.println(p.get(i).name);
                            System.out.println(p.get(i).time_quantum_1);
                            System.out.println("after ceil");
                            System.out.println(p.get(i).time_quantum);
                        }
                        process pr = new process();
                        p.get(pos).burst = p.get(pos).burst - p.get(pos).time_quantum;
                        if (p.get(pos).burst <= 0) {
                            while (p.get(pos).burst < 0) {
                                p.get(pos).time_quantum--;
                                p.get(pos).burst++;
                            }
                            System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                            p.get(pos).burst = 0;
                            p.get(pos).time_quantum = 0;
                            p.get(pos).arrival = p.get(pos).time_quantum;
                            counter += p.get(pos).arrival;
                            pr.turnaround_time = p.get(pos).arrival;
                            p.get(pos).finished = false;
                        } else {
                            counter += p.get(pos).time_quantum;
                            p.get(i).arrival = p.get(pos).time_quantum;
                        }
                        pr.burst = p.get(pos).burst;
                        int size = queue.size();
                        pr.name = p.get(pos).name;
                        pr.arrival = queue.get(size - 1).arrival + p.get(pos).arrival;
                        pr.time_quantum_1 = p.get(pos).time_quantum_1;
                        pr.time_quantum = p.get(pos).time_quantum;
                        pr.burst_time_1=p.get(i).burst_time_1;
                        pr.waiting_time=p.get(i).burst_time_1;
                        pr.rest_time_q = p.get(pos).rest_time_q;
                        pr.finished = p.get(pos).finished;
                        pr.arrival_time_1 = p.get(pos).arrival_time_1;
                        queue.add(pr);


                    }
                }
            } else {
                int pos = 0;
                pos = index(p, i);
                int size = queue.size();
                if (p.get(pos).name.equals(queue.get(size - 1).name)) {
                    pos = index(p, i - 1);
                }
                if (p.get(pos).time_quantum % 2 == 0) {
                    p.get(pos).time_quantum = p.get(pos).time_quantum / 2;
                    p.get(pos).rest_time_q = p.get(pos).time_quantum;
                    System.out.println(p.get(i).name);
                    System.out.println(p.get(i).time_quantum_1);
                    System.out.println("after ceil");
                    System.out.println(p.get(i).time_quantum);
                } else {
                    p.get(pos).time_quantum = (p.get(pos).time_quantum / 2) + 1;
                    p.get(pos).rest_time_q = p.get(pos).time_quantum_1 - p.get(pos).time_quantum;
                    System.out.println(p.get(i).name);
                    System.out.println(p.get(i).time_quantum_1);
                    System.out.println("after ceil");
                    System.out.println(p.get(i).time_quantum);
                }
                process pr = new process();
                p.get(pos).burst = p.get(pos).burst - p.get(pos).time_quantum;
                if (p.get(pos).burst <= 0) {
                    while (p.get(pos).burst < 0) {
                        p.get(pos).time_quantum--;
                        p.get(pos).burst++;
                    }
                    System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                    p.get(pos).burst = 0;
                    p.get(pos).time_quantum = 0;
                    p.get(pos).arrival = p.get(pos).time_quantum;
                    counter += p.get(pos).arrival;
                    pr.turnaround_time = p.get(pos).arrival;
                    p.get(pos).finished = false;
                } else {
                    counter += p.get(pos).time_quantum;
                    p.get(pos).arrival = p.get(pos).time_quantum;
                }
                pr.burst = p.get(pos).burst;
                size = queue.size();
                pr.name = p.get(pos).name;
                pr.arrival = queue.get(size - 1).arrival + p.get(pos).arrival;
                pr.time_quantum_1 = p.get(pos).time_quantum_1;
                pr.time_quantum = p.get(pos).time_quantum;
                pr.burst_time_1=p.get(i).burst_time_1;
                pr.waiting_time=p.get(i).burst_time_1;
                pr.rest_time_q = p.get(pos).rest_time_q;
                pr.finished = p.get(pos).finished;
                pr.arrival_time_1 = p.get(pos).arrival_time_1;
                queue.add(pr);


            }
        }
        int i = 0;



        while (x) {
            int size=queue.size()-1;
            i = index(p, p.size());


            if (p.get(i).time_quantum % 2 == 0) {
                p.get(i).time_quantum = p.get(i).time_quantum / 2;
                p.get(i).rest_time_q = p.get(i).time_quantum;
                System.out.println(p.get(i).name);
                System.out.println(p.get(i).time_quantum_1);
                System.out.println("after ceil");
                System.out.println(p.get(i).time_quantum);
            } else {
                p.get(i).time_quantum = p.get(i).time_quantum / 2 + 1;
                p.get(i).rest_time_q = p.get(i).time_quantum_1 - p.get(i).time_quantum;
                System.out.println(p.get(i).name);
                System.out.println(p.get(i).time_quantum_1);
                System.out.println("after ceil");
                System.out.println(p.get(i).time_quantum);
            }
            process pr = new process();
            p.get(i).burst = p.get(i).burst - p.get(i).time_quantum;
            p.get(i).arrival=p.get(i).time_quantum;

            if (p.get(i).burst <= 0) {
                while (p.get(i).burst < 0) {
                    p.get(i).time_quantum--;
                    p.get(i).burst++;
                }
                System.out.println("the process "+p.get(i).name+"  has finished and q = 0");

                p.get(i).burst = 0;
                p.get(i).arrival = p.get(i).time_quantum;
                p.get(i).time_quantum = 0;

                pr.turnaround_time = p.get(i).arrival;
                p.get(i).finished = false;
            }
            int pos=index(p, p.size());
             if(pos == i ) {
                p.get(i).burst = p.get(i).burst - p.get(i).rest_time_q;
                if (p.get(i).burst <= 0) {
                    while (p.get(i).burst < 0) {
                        p.get(i).time_quantum_1--;
                        p.get(i).burst++;
                    }
                    System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
                    p.get(i).finished = false;
                    p.get(i).arrival = p.get(i).time_quantum_1;
                    pr.turnaround_time = p.get(i).arrival;
                } else if (p.get(i).burst > 0) {
                    p.get(i).arrival = p.get(i).time_quantum_1;
                    p.get(i).time_quantum_1 += 1;
                    p.get(i).time_quantum = p.get(i).time_quantum_1;
                }

            }
            pr.burst = p.get(i).burst;
             size = queue.size();
            pr.name = p.get(i).name;
            pr.arrival = queue.get(size - 1).arrival + p.get(i).arrival;
            pr.time_quantum_1 = p.get(i).time_quantum_1;
            pr.time_quantum = p.get(i).time_quantum;
            pr.rest_time_q = p.get(i).rest_time_q;
            pr.burst_time_1=p.get(i).burst_time_1;
            pr.waiting_time=p.get(i).burst_time_1;
            pr.finished = p.get(i).finished;
            pr.arrival_time_1 = p.get(i).arrival_time_1;
            queue.add(pr);
 pos=index(p,p.size());
if(p.get(i).burst>0&&pos==i){
    i=index_1(p,p.size(),pos);
    if (p.get(i).time_quantum % 2 == 0) {
        p.get(i).time_quantum = p.get(i).time_quantum / 2;
        p.get(i).rest_time_q = p.get(i).time_quantum;
        System.out.println(p.get(i).name);
        System.out.println(p.get(i).time_quantum_1);
        System.out.println("after ceil");
        System.out.println(p.get(i).time_quantum);
    } else {
        p.get(i).time_quantum = p.get(i).time_quantum / 2 + 1;
        p.get(i).rest_time_q = p.get(i).time_quantum_1 - p.get(i).time_quantum;
        System.out.println(p.get(i).name);
        System.out.println(p.get(i).time_quantum_1);
        System.out.println("after ceil");
        System.out.println(p.get(i).time_quantum);
    }
    process pro = new process();
    p.get(i).burst = p.get(i).burst - p.get(i).time_quantum;
    p.get(i).arrival=p.get(i).time_quantum;



    if (p.get(i).burst <= 0) {
        while (p.get(i).burst < 0) {
            p.get(i).time_quantum--;
            p.get(i).burst++;
        }
        System.out.println("the process "+p.get(i).name+"  has finished and q = 0");
        p.get(i).burst = 0;
        p.get(i).time_quantum = 0;
        p.get(i).arrival = p.get(i).time_quantum;

        pro.turnaround_time = p.get(i).arrival;
        p.get(i).finished = false;
    }
    pos=index(p, p.size());
    if(pos == i ) {
        p.get(i).burst = p.get(i).burst - p.get(i).rest_time_q;
        if (p.get(i).burst <= 0) {
            while (p.get(i).burst < 0) {
                p.get(i).time_quantum_1--;
                p.get(i).burst++;
            }
            p.get(i).finished = false;
            p.get(i).arrival = p.get(i).time_quantum_1;
            pro.turnaround_time = p.get(i).arrival;
        } else if (p.get(i).burst > 0) {
            p.get(i).arrival = p.get(i).time_quantum_1;
            p.get(i).time_quantum_1 += 1;
            p.get(i).time_quantum = p.get(i).time_quantum_1;
        }

    }
    pro.burst = p.get(i).burst;
    size = queue.size();
    pro.name = p.get(i).name;
    pro.arrival = queue.get(size - 1).arrival + p.get(i).arrival;
    pro.time_quantum_1 = p.get(i).time_quantum_1;
    pro.time_quantum = p.get(i).time_quantum;
    pro.rest_time_q = p.get(i).rest_time_q;
    pro.finished = p.get(i).finished;
    pro.arrival_time_1 = p.get(i).arrival_time_1;
    pro.burst_time_1=p.get(i).burst_time_1;
    pro.waiting_time=p.get(i).burst_time_1;

    queue.add(pro);

}
            int y = 0;
            for (int j = 0; j < p.size(); j++){
                y+=p.get(j).burst;
        }
        if (y==0)
        {
            x = false;
        }

    }
    int turn_aver=0;
       int aver_waiting_time=0;
       System.out.println(0);
for (int k=0;k<queue.size();k++){
            System.out.println(queue.get(k).name);
            System.out.println(queue.get(k).arrival);
            if(queue.get(k).turnaround_time!=0){
                queue.get(k).turnaround_time=queue.get(k).arrival-queue.get(k).arrival_time_1;
                queue.get(k).waiting_time=queue.get(k).turnaround_time-queue.get(k).burst_time_1;
                System.out.println("the turnarround time for process is  ");
                System.out.println(queue.get(k).turnaround_time);
                System.out.println("the waiting time for process is  ");
                System.out.println(queue.get(k).waiting_time);
                turn_aver+=queue.get(k).turnaround_time;
                aver_waiting_time+=queue.get(k).waiting_time;
            }


}

        System.out.println(" averager turnarrround time is");
            turn_aver=turn_aver/p.size();
        System.out.println(turn_aver);
        System.out.println(" averager waiting time is");
        aver_waiting_time=aver_waiting_time/p.size();
        System.out.println(aver_waiting_time);



   }








        public static void main(String[] args) {
         //   ArrayList<process>pro= new ArrayList<>();
//            int [] burst=new int[5];
//            burst[0]=17;
//            burst[1]=6;
//            burst[2]=11;
//            burst[3]=4;
//
//            int [] arr=new int[5];
//            arr[0]=0;
//            arr[1]=2;
//            arr[2]=5;
//            arr[3]=15;
//            int [] q=new int[5];
//            q[0]=7;
//            q[1]=9;
//            q[2]=4;
//            q[3]=2;
//            System.out.println("Enter numbers of processes");
//            Scanner scan=new Scanner(System.in);
          //  int num_process=scan.nextInt();
//            for(int i=0;i<4;i++){
//                process p=new process();
//                /*System.out.println("Enter process name");
//                p.name= scan.next();
//                System.out.println("Enter process burst time");
//                p.burst=scan.nextInt();*/
//                p.name="p"+(i+1);
//                p.burst=burst[i];
//              /*  System.out.println("Enter process arrival time" );
//                p.arrival=scan.nextInt();*/
//              p.arrival=arr[i];
//              p.time_quantum=q[i];
//                p.finished=true;
//                pro.add(p);
//            }
//            shortest_job a=new shortest_job();
//            a.AG(pro);
//           // a.sjf(pro);
            System.out.println("if you want SJF enter 1");
            System.out.println("if you want AG enter 2");
            Scanner s=new Scanner(System.in);
            int x=s.nextInt();
            if(x==1){
                ArrayList<process> pro= new ArrayList<>();
                System.out.println("Enter numbers of processes");
                Scanner scan=new Scanner(System.in);
                int num_process=scan.nextInt();
                for(int i=0;i<num_process;i++){
                    process p=new process();
                    System.out.println("Enter process name");
                    p.name= scan.next();
                    System.out.println("Enter process burst time");
                    p.burst=scan.nextInt();
                    System.out.println("Enter process arrival time" );
                    p.arrival=scan.nextInt();
                    p.finished=true;
                    pro.add(p);

                }
                shortest_job a=new shortest_job();
                a.sjf(pro);
            }
            if(x==2){
                ArrayList<process> pro= new ArrayList<>();
                System.out.println("Enter numbers of processes");
                Scanner scan=new Scanner(System.in);
                int num_process=scan.nextInt();
                for(int i=0;i<num_process;i++){
                    process p=new process();
                    System.out.println("Enter process name");
                    p.name= scan.next();
                    System.out.println("Enter process burst time");
                    p.burst=scan.nextInt();
                    System.out.println("Enter process arrival time" );
                    p.arrival=scan.nextInt();
                    System.out.println("Enter process Quantum time");
                    p.time_quantum=scan.nextInt();
                    p.finished=true;
                    pro.add(p);


                }
                shortest_job a=new shortest_job();
                a.AG(pro);
            }
       }


}
