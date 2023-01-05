package paket;
import java.util.*;

import java.io.*;

public class Dispatcher {
    public static void main(String[] args) {

        String filePath = "C:\\Users\\Eren Bekmezci\\Desktop\\Project\\src\\paket\\giris.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int sistem_sn=0;
            boolean askıda = false;
            LinkedList<Process> input_queue = new LinkedList<Process>();
            LinkedList<Process> ready_queue = new LinkedList<Process>();
            LinkedList<Process> real_time_queue = new LinkedList<Process>();
            LinkedList<Process> user_time_queue = new LinkedList<Process>();
            LinkedList<Process> priority_one_queue = new LinkedList<Process>();
            LinkedList<Process> priority_two_queue = new LinkedList<Process>();
            LinkedList<Process> priority_three_queue =new LinkedList<Process>();

            int id = 0;
            //input kuyruguna giris.txt deki veriler eklendi "Bekleme kuyrugu"

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                int arrival_time = Integer.parseInt(parts[0]);
                int priority = Integer.parseInt(parts[1]);
                int process_time = Integer.parseInt(parts[2]);

                Process p = new Process(arrival_time, priority, process_time, id);



                input_queue.add(p);
                id++;

            }
            br.close();
            while(!input_queue.isEmpty() || !ready_queue.isEmpty())
            {
                if(!input_queue.isEmpty())
                {
                    for(int i = 0 ; i<input_queue.size() ; i++)
                    {
                        if(input_queue.get(i).arrival_time == sistem_sn)
                        {
                            ready_queue.add(input_queue.get(i));
                        }
                        input_queue.remove(input_queue.get(i));
                    }
                }


                // ready queue dekileri uygun öncelilkli kuyruklara atma

                for(int i = 0 ; i<ready_queue.size() ; i++)
                {
                    if (ready_queue.get(i).priority == 0)
                    {
                        user_time_queue.add(ready_queue.get(i));
                        real_time_queue.add(ready_queue.get(i));
                        ready_queue.remove(ready_queue.get(i));
                    }
                    else if (ready_queue.get(i).priority == 1)
                    {
                        user_time_queue.add(ready_queue.get(i));

                        priority_one_queue.add(ready_queue.get(i));
                        ready_queue.remove(ready_queue.get(i));

                    }
                    else if (ready_queue.get(i).priority == 2)
                    {
                        user_time_queue.add(ready_queue.get(i));

                        priority_two_queue.add(ready_queue.get(i));
                        ready_queue.remove(ready_queue.get(i));

                    }
                    else if (ready_queue.get(i).priority == 3)
                    {
                        user_time_queue.add(ready_queue.get(i));

                        priority_three_queue.add(ready_queue.get(i));
                        ready_queue.remove(ready_queue.get(i));

                    }

                }

                while (!real_time_queue.isEmpty())
                {
                    askıda = false;

                    while(!real_time_queue.getFirst().isDone())
                    {
                        real_time_queue.getFirst().run(sistem_sn);
                        sistem_sn ++;


                        //fcfs çalışırken arka planda ready quque input kuyuklarını alır
                        if(!input_queue.isEmpty())
                        {
                            for(int i = 0 ; i<input_queue.size() ; i++)
                            {
                                if(input_queue.get(i).arrival_time == sistem_sn)
                                {
                                    ready_queue.add(input_queue.get(i));
                                }
                                input_queue.remove(input_queue.get(i));
                            }
                        }
                    }
                    real_time_queue.removeFirst(); //ilk sıradakini çıkarıyoruz proses sonlandığında

                    //fcfs çalışırken read queue daki prosesler uygun önceliğe göre yerleştirilir
                    for(int i = 0 ; i<ready_queue.size() ; i++)
                    {
                        if (ready_queue.get(i).priority == 0)
                        {
                            user_time_queue.add(ready_queue.get(i));
                            real_time_queue.add(ready_queue.get(i));
                            ready_queue.remove(ready_queue.get(i));
                        }
                        else if (ready_queue.get(i).priority == 1)
                        {
                            user_time_queue.add(ready_queue.get(i));

                            priority_one_queue.add(ready_queue.get(i));
                            ready_queue.remove(ready_queue.get(i));

                        }
                        else if (ready_queue.get(i).priority == 2)
                        {
                            user_time_queue.add(ready_queue.get(i));

                            priority_two_queue.add(ready_queue.get(i));
                            ready_queue.remove(ready_queue.get(i));

                        }
                        else if (ready_queue.get(i).priority == 3)
                        {
                            user_time_queue.add(ready_queue.get(i));

                            priority_three_queue.add(ready_queue.get(i));
                            ready_queue.remove(ready_queue.get(i));

                        }

                    }





                }
                //burada user time yerine 1 ,2 ,3 . kuyrukları sorgula user time boşuna yer kaplıyor
                while(!user_time_queue.isEmpty() && real_time_queue.isEmpty() && !askıda)
                {
                    switch(user_time_queue.getFirst().getPriority()) //quantum 1 sn yani eğer işletilen proses
                            //1 sn de bitmezse bir alt öncelikli kuyruğa gönder ve sıradaki prosesi işlet
                    {
                        case 1:
                            while(!priority_one_queue.isEmpty() && !askıda)
                            {
                                //quantum 1 sn

                                priority_one_queue.getFirst().run(sistem_sn);
                                sistem_sn ++;

                                //1 sn işletildikten sonra kontrol edilir
                                if(!priority_one_queue.getFirst().isDone())
                                {
                                    //burada askıya al ve herhangi bir user kuyrukta tekrar çalıştığında 20 sn geçmiş
                                    //ise üzerinden proses zaman aşımına uğrar bunu hallet
                                    priority_two_queue.add(priority_one_queue.getFirst());
                                    priority_one_queue.removeFirst();
                                }

                                //queue1 çalışırken arka planda ready quque input kuyuklarını alır

                                if(!input_queue.isEmpty())
                                {
                                    for(int i = 0 ; i<input_queue.size() ; i++)
                                    {
                                        if(input_queue.get(i).arrival_time == sistem_sn)
                                        {
                                            ready_queue.add(input_queue.get(i));
                                        }
                                        input_queue.remove(input_queue.get(i));
                                    }
                                }

                                //queue1 çalışırken ready queue daki prosesler uygun önceliğe göre yerleştirilir
                                for(int i = 0 ; i<ready_queue.size() ; i++)
                                {
                                    if (ready_queue.get(i).priority == 0)
                                    {
                                        user_time_queue.add(ready_queue.get(i));
                                        real_time_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));
                                    }
                                    else if (ready_queue.get(i).priority == 1)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_one_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }
                                    else if (ready_queue.get(i).priority == 2)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_two_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }
                                    else if (ready_queue.get(i).priority == 3)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_three_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }

                                }


                                if(!real_time_queue.isEmpty())
                                {
                                    askıda = true;
                                    break;
                                }


                            }


                        case 2:
                            while(!priority_two_queue.isEmpty() && !askıda)
                            {
                                //quantum 1 sn

                                priority_two_queue.getFirst().run(sistem_sn);
                                sistem_sn ++;

                                //1 sn işletildikten sonra kontrol edilir
                                if(!priority_two_queue.getFirst().isDone())
                                {
                                    //burada askıya al ve herhangi bir user kuyrukta tekrar çalıştığında 20 sn geçmiş
                                    //ise üzerinden proses zaman aşımına uğrar bunu hallet
                                    priority_three_queue.add(priority_two_queue.getFirst());
                                    priority_two_queue.removeFirst();
                                }


                                if(!input_queue.isEmpty())
                                {
                                    for(int i = 0 ; i<input_queue.size() ; i++)
                                    {
                                        if(input_queue.get(i).arrival_time == sistem_sn)
                                        {
                                            ready_queue.add(input_queue.get(i));
                                        }
                                        input_queue.remove(input_queue.get(i));
                                    }
                                }

                                //queue1 çalışırken ready queue daki prosesler uygun önceliğe göre yerleştirilir
                                for(int i = 0 ; i<ready_queue.size() ; i++)
                                {
                                    if (ready_queue.get(i).priority == 0)
                                    {
                                        user_time_queue.add(ready_queue.get(i));
                                        real_time_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));
                                    }
                                    else if (ready_queue.get(i).priority == 1)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_one_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }
                                    else if (ready_queue.get(i).priority == 2)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_two_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }
                                    else if (ready_queue.get(i).priority == 3)
                                    {
                                        user_time_queue.add(ready_queue.get(i));

                                        priority_three_queue.add(ready_queue.get(i));
                                        ready_queue.remove(ready_queue.get(i));

                                    }

                                }

                                if(!real_time_queue.isEmpty())
                                {
                                    askıda = true;
                                    break;
                                }


                            }
                        //case 3: //round robin
//                            while(!priority_three_queue.isEmpty() && !askıda) //askıda olabilir mi bu adıma gelince sorgula ?
//                            {
//                                //quantum 1 sn
//                                //sonraki düğüme geçmek için iterator kullanımı
//                                Iterator<Process> it = priority_three_queue.iterator();
//
//                                priority_three_queue.getFirst().run(sistem_sn);
//
//
//
//                                sistem_sn ++;
//
//                                //1 sn işletildikten sonra kontrol edilir
//                                if(priority_three_queue.getFirst().isDone())
//                                {
//                                    priority_three_queue.removeFirst();
//                                }
//                                else
//                                {
//                                    if(it.hasNext())
//                                    {
//
//                                    }
//                                    else
//                                    {
//
//                                    }
//                                }
//
//                                if(real_time_queue.isEmpty())
//                                {
//                                    askıda = true;
//                                    break;
//                                }
//
//
//                            }
                    }
                }




            }









        }
        catch (IOException iox){
            System.out.println(filePath+" adli dosya okunamiyor.");
        }


        
    }

}

