import java.util.Random;
import java.util.Arrays.*;

class GA
{
    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;
      

    public GA() 
    {
        
    }

    public static void main(String []args)
    {
        
        GA agent = new GA();

        Random rn  = new Random();

        agent.population.initializePopulation(7);
        agent.population.calculateFitness();

        System.out.println("Generation : " + agent.generationCount + "      Fittest : " + agent.population.fittest);

        while(agent.population.fittest != 8)
        {
            agent.generationCount++;
            agent.selection();
            //System.out.println("Selction Done");
            agent.crossover();
            //System.out.println("CrossoverDone Done");
            if(rn.nextInt()%7 < 3)
            {
                agent.mutation();
            }
            agent.addFittestOffspring();
            agent.population.calculateFitness();

            System.out.println("Generation : " + agent.generationCount + "    Fittest : " + agent.population.fittest);

        }

        System.out.println("Solution found in generation " + agent.generationCount);
        System.out.println("Fitness :" + agent.population.getFittest().fitness);
        System.out.println("Genes : ");

        for(int i=0;i<8;i++)
        {
            System.out.print(agent.population.getFittest().genes[i]);
        }

        
        System.out.println("");



        
    }

    public void selection()
    {
        fittest = population.getFittest();
        secondFittest = population.getSecondFittest();
    }

    public void crossover()
    {
        Random rn = new Random();

        int crossOverPoint = rn.nextInt()%7  ;

        for(int i=0;i<=crossOverPoint;i++)
        {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;
        }
    }

    public void mutation()
    {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        //Flip values at the mutation point
        if (fittest.genes[mutationPoint] == 0) 
        {
            fittest.genes[mutationPoint] = 1;
        } else 
        {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) 
        {
            secondFittest.genes[mutationPoint] = 1;
        } else 
        {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    public Individual getFittestOffspring()
    {
        if (fittest.fitness > secondFittest.fitness) 
        {
            return fittest;
        }
        return secondFittest;
    }

    public void addFittestOffspring()
    {
        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }
  



public class Individual
{
    int fitness;  
    int []genes = new int[8];
    int geneLength = 8;

    public Individual()
    {   
        Random rn = new Random();
        for(int i=0;i<geneLength;i++)
        {
            genes[i] = Math.abs(rn.nextInt()%2);
            System.out.print(genes[i]);
        }
        System.out.println();

        fitness = 0;
    }

    public void calcFitness()
    {
        fitness = 0;
        for(int i=0;i<8;i++)
        {
            if(genes[i] == 1)
            {
                fitness++;
            }
        }
    }

}


public class Population
{
    int populationSize = 7;
    Individual []individuals = new Individual[7];
    int fittest = 0;

   // System.out.println(" Population length : " + populationSize);

    public void initializePopulation(int size)
    {
        for(int i=0;i<size;i++)
        {
            individuals[i] = new Individual();
        }
    }

    public Individual getFittest()
    {
        int maxFit = Integer.MIN_VALUE;
        int maxFitIndex = 0;
        for(int i=0;i<individuals.length;i++)
        {
            if(maxFit <= individuals[i].fitness)
            {
                maxFit = individuals[i].fitness;
                maxFitIndex = i;
            }
        }

        fittest = individuals[maxFitIndex].fitness;
        return individuals[maxFitIndex];
    }

    public Individual getSecondFittest() 
    {
        int maxFit1 = 0;
        int maxFit2 = 0;
        for (int i = 0; i < individuals.length; i++) 
        {
            if (individuals[i].fitness > individuals[maxFit1].fitness) 
            {
                maxFit2 = maxFit1;
                maxFit1 = i;
            } 
            else if (individuals[i].fitness > individuals[maxFit2].fitness) 
            {
                maxFit2 = i;
            }
        }
        return individuals[maxFit2];
    }

    //Get index of least fittest individual
    public int getLeastFittestIndex() 
    {
        int minFitVal = Integer.MAX_VALUE;
        int minFitIndex = 0;
        for (int i = 0; i < individuals.length; i++) 
        {
            if (minFitVal >= individuals[i].fitness) 
            {
                minFitVal = individuals[i].fitness;
                minFitIndex = i;
            }
        }
        return minFitIndex;
    }

    public void calculateFitness() 
    {

        for (int i = 0; i < individuals.length; i++) 
        {
            individuals[i].calcFitness();
        }
        getFittest();
    }


}
}




//Output:

/*
  00111001
00101100
10001010
11010010
10110000
00100001
10011011
Generation : 0      Fittest : 5
Generation : 1    Fittest : 5
Generation : 2    Fittest : 5
Generation : 3    Fittest : 5
Generation : 4    Fittest : 4
Generation : 5    Fittest : 4
Generation : 6    Fittest : 6
Generation : 7    Fittest : 6
Generation : 8    Fittest : 6
Generation : 9    Fittest : 6
Generation : 10    Fittest : 6
Generation : 11    Fittest : 4
Generation : 12    Fittest : 2
Generation : 13    Fittest : 2
Generation : 14    Fittest : 0
Generation : 15    Fittest : 2
Generation : 16    Fittest : 2
Generation : 17    Fittest : 4
Generation : 18    Fittest : 2
Generation : 19    Fittest : 2
Generation : 20    Fittest : 2
Generation : 21    Fittest : 2
Generation : 22    Fittest : 2
Generation : 23    Fittest : 2
Generation : 24    Fittest : 4
Generation : 25    Fittest : 4
Generation : 26    Fittest : 4
Generation : 27    Fittest : 4
Generation : 28    Fittest : 2
Generation : 29    Fittest : 4
Generation : 30    Fittest : 4
Generation : 31    Fittest : 6
Generation : 32    Fittest : 4
Generation : 33    Fittest : 4
Generation : 34    Fittest : 4
Generation : 35    Fittest : 4
Generation : 36    Fittest : 4
Generation : 37    Fittest : 4
Generation : 38    Fittest : 4
Generation : 39    Fittest : 4
Generation : 40    Fittest : 4
Generation : 41    Fittest : 4
Generation : 42    Fittest : 4
Generation : 43    Fittest : 4
Generation : 44    Fittest : 4
Generation : 45    Fittest : 4
Generation : 46    Fittest : 6
Generation : 47    Fittest : 4
Generation : 48    Fittest : 2
Generation : 49    Fittest : 4
Generation : 50    Fittest : 4
Generation : 51    Fittest : 4
Generation : 52    Fittest : 4
Generation : 53    Fittest : 4
Generation : 54    Fittest : 6
Generation : 55    Fittest : 6
Generation : 56    Fittest : 6
Generation : 57    Fittest : 6
Generation : 58    Fittest : 6
Generation : 59    Fittest : 6
Generation : 60    Fittest : 6
Generation : 61    Fittest : 6
Generation : 62    Fittest : 4
Generation : 63    Fittest : 4
Generation : 64    Fittest : 4
Generation : 65    Fittest : 4
Generation : 66    Fittest : 4
Generation : 67    Fittest : 4
Generation : 68    Fittest : 4
Generation : 69    Fittest : 6
Generation : 70    Fittest : 6
Generation : 71    Fittest : 6
Generation : 72    Fittest : 6
Generation : 73    Fittest : 4
Generation : 74    Fittest : 4
Generation : 75    Fittest : 4
Generation : 76    Fittest : 4
Generation : 77    Fittest : 4
Generation : 78    Fittest : 6
Generation : 79    Fittest : 6
Generation : 80    Fittest : 4
Generation : 81    Fittest : 4
Generation : 82    Fittest : 4
Generation : 83    Fittest : 6
Generation : 84    Fittest : 6
Generation : 85    Fittest : 6
Generation : 86    Fittest : 6
Generation : 87    Fittest : 6
Generation : 88    Fittest : 6
Generation : 89    Fittest : 6
Generation : 90    Fittest : 4
Generation : 91    Fittest : 6
Generation : 92    Fittest : 6
Generation : 93    Fittest : 6
Generation : 94    Fittest : 6
Generation : 95    Fittest : 4
Generation : 96    Fittest : 4
Generation : 97    Fittest : 2
Generation : 98    Fittest : 2
Generation : 99    Fittest : 2
Generation : 100    Fittest : 4
Generation : 101    Fittest : 2
Generation : 102    Fittest : 2
Generation : 103    Fittest : 2
Generation : 104    Fittest : 2
Generation : 105    Fittest : 0
Generation : 106    Fittest : 2
Generation : 107    Fittest : 4
Generation : 108    Fittest : 4
Generation : 109    Fittest : 6
Generation : 110    Fittest : 4
Generation : 111    Fittest : 4
Generation : 112    Fittest : 4
Generation : 113    Fittest : 4
Generation : 114    Fittest : 2
Generation : 115    Fittest : 4
Generation : 116    Fittest : 2
Generation : 117    Fittest : 2
Generation : 118    Fittest : 2
Generation : 119    Fittest : 4
Generation : 120    Fittest : 4
Generation : 121    Fittest : 4
Generation : 122    Fittest : 4
Generation : 123    Fittest : 4
Generation : 124    Fittest : 4
Generation : 125    Fittest : 4
Generation : 126    Fittest : 4
Generation : 127    Fittest : 4
Generation : 128    Fittest : 4
Generation : 129    Fittest : 4
Generation : 130    Fittest : 6
Generation : 131    Fittest : 4
Generation : 132    Fittest : 2
Generation : 133    Fittest : 2
Generation : 134    Fittest : 2
Generation : 135    Fittest : 2
Generation : 136    Fittest : 4
Generation : 137    Fittest : 4
Generation : 138    Fittest : 4
Generation : 139    Fittest : 6
Generation : 140    Fittest : 4
Generation : 141    Fittest : 4
Generation : 142    Fittest : 6
Generation : 143    Fittest : 6
Generation : 144    Fittest : 6
Generation : 145    Fittest : 6
Generation : 146    Fittest : 4
Generation : 147    Fittest : 4
Generation : 148    Fittest : 4
Generation : 149    Fittest : 4
Generation : 150    Fittest : 4
Generation : 151    Fittest : 4
Generation : 152    Fittest : 4
Generation : 153    Fittest : 2
Generation : 154    Fittest : 4
Generation : 155    Fittest : 4
Generation : 156    Fittest : 4
Generation : 157    Fittest : 4
Generation : 158    Fittest : 4
Generation : 159    Fittest : 4
Generation : 160    Fittest : 4
Generation : 161    Fittest : 4
Generation : 162    Fittest : 4
Generation : 163    Fittest : 4
Generation : 164    Fittest : 4
Generation : 165    Fittest : 4
Generation : 166    Fittest : 4
Generation : 167    Fittest : 4
Generation : 168    Fittest : 4
Generation : 169    Fittest : 4
Generation : 170    Fittest : 4
Generation : 171    Fittest : 2
Generation : 172    Fittest : 4
Generation : 173    Fittest : 4
Generation : 174    Fittest : 4
Generation : 175    Fittest : 4
Generation : 176    Fittest : 4
Generation : 177    Fittest : 4
Generation : 178    Fittest : 4
Generation : 179    Fittest : 4
Generation : 180    Fittest : 4
Generation : 181    Fittest : 4
Generation : 182    Fittest : 4
Generation : 183    Fittest : 2
Generation : 184    Fittest : 4
Generation : 185    Fittest : 4
Generation : 186    Fittest : 4
Generation : 187    Fittest : 4
Generation : 188    Fittest : 6
Generation : 189    Fittest : 6
Generation : 190    Fittest : 4
Generation : 191    Fittest : 4
Generation : 192    Fittest : 4
Generation : 193    Fittest : 4
Generation : 194    Fittest : 2
Generation : 195    Fittest : 2
Generation : 196    Fittest : 0
Generation : 197    Fittest : 2
Generation : 198    Fittest : 4
Generation : 199    Fittest : 4
Generation : 200    Fittest : 4
Generation : 201    Fittest : 4
Generation : 202    Fittest : 4
Generation : 203    Fittest : 4
Generation : 204    Fittest : 4
Generation : 205    Fittest : 6
Generation : 206    Fittest : 4
Generation : 207    Fittest : 4
Generation : 208    Fittest : 4
Generation : 209    Fittest : 4
Generation : 210    Fittest : 4
Generation : 211    Fittest : 2
Generation : 212    Fittest : 2
Generation : 213    Fittest : 4
Generation : 214    Fittest : 4
Generation : 215    Fittest : 4
Generation : 216    Fittest : 4
Generation : 217    Fittest : 6
Generation : 218    Fittest : 6
Generation : 219    Fittest : 6
Generation : 220    Fittest : 4
Generation : 221    Fittest : 4
Generation : 222    Fittest : 4
Generation : 223    Fittest : 4
Generation : 224    Fittest : 2
Generation : 225    Fittest : 2
Generation : 226    Fittest : 4
Generation : 227    Fittest : 4
Generation : 228    Fittest : 4
Generation : 229    Fittest : 6
Generation : 230    Fittest : 4
Generation : 231    Fittest : 4
Generation : 232    Fittest : 4
Generation : 233    Fittest : 4
Generation : 234    Fittest : 4
Generation : 235    Fittest : 6
Generation : 236    Fittest : 6
Generation : 237    Fittest : 6
Generation : 238    Fittest : 4
Generation : 239    Fittest : 2
Generation : 240    Fittest : 2
Generation : 241    Fittest : 4
Generation : 242    Fittest : 4
Generation : 243    Fittest : 6
Generation : 244    Fittest : 6
Generation : 245    Fittest : 6
Generation : 246    Fittest : 6
Generation : 247    Fittest : 4
Generation : 248    Fittest : 4
Generation : 249    Fittest : 4
Generation : 250    Fittest : 6
Generation : 251    Fittest : 4
Generation : 252    Fittest : 4
Generation : 253    Fittest : 4
Generation : 254    Fittest : 4
Generation : 255    Fittest : 4
Generation : 256    Fittest : 4
Generation : 257    Fittest : 6
Generation : 258    Fittest : 6
Generation : 259    Fittest : 6
Generation : 260    Fittest : 4
Generation : 261    Fittest : 6
Generation : 262    Fittest : 6
Generation : 263    Fittest : 4
Generation : 264    Fittest : 2
Generation : 265    Fittest : 2
Generation : 266    Fittest : 2
Generation : 267    Fittest : 2
Generation : 268    Fittest : 2
Generation : 269    Fittest : 4
Generation : 270    Fittest : 4
Generation : 271    Fittest : 4
Generation : 272    Fittest : 4
Generation : 273    Fittest : 6
Generation : 274    Fittest : 6
Generation : 275    Fittest : 6
Generation : 276    Fittest : 4
Generation : 277    Fittest : 2
Generation : 278    Fittest : 2
Generation : 279    Fittest : 0
Generation : 280    Fittest : 0
Generation : 281    Fittest : 0
Generation : 282    Fittest : 0
Generation : 283    Fittest : 2
Generation : 284    Fittest : 2
Generation : 285    Fittest : 4
Generation : 286    Fittest : 6
Generation : 287    Fittest : 6
Generation : 288    Fittest : 4
Generation : 289    Fittest : 2
Generation : 290    Fittest : 2
Generation : 291    Fittest : 2
Generation : 292    Fittest : 2
Generation : 293    Fittest : 2
Generation : 294    Fittest : 2
Generation : 295    Fittest : 2
Generation : 296    Fittest : 2
Generation : 297    Fittest : 2
Generation : 298    Fittest : 4
Generation : 299    Fittest : 4
Generation : 300    Fittest : 4
Generation : 301    Fittest : 2
Generation : 302    Fittest : 4
Generation : 303    Fittest : 2
Generation : 304    Fittest : 2
Generation : 305    Fittest : 2
Generation : 306    Fittest : 4
Generation : 307    Fittest : 4
Generation : 308    Fittest : 4
Generation : 309    Fittest : 4
Generation : 310    Fittest : 4
Generation : 311    Fittest : 4
Generation : 312    Fittest : 4
Generation : 313    Fittest : 4
Generation : 314    Fittest : 4
Generation : 315    Fittest : 4
Generation : 316    Fittest : 4
Generation : 317    Fittest : 4
Generation : 318    Fittest : 4
Generation : 319    Fittest : 4
Generation : 320    Fittest : 4
Generation : 321    Fittest : 2
Generation : 322    Fittest : 2
Generation : 323    Fittest : 2
Generation : 324    Fittest : 2
Generation : 325    Fittest : 4
Generation : 326    Fittest : 4
Generation : 327    Fittest : 4
Generation : 328    Fittest : 4
Generation : 329    Fittest : 4
Generation : 330    Fittest : 4
Generation : 331    Fittest : 4
Generation : 332    Fittest : 4
Generation : 333    Fittest : 4
Generation : 334    Fittest : 4
Generation : 335    Fittest : 4
Generation : 336    Fittest : 2
Generation : 337    Fittest : 4
Generation : 338    Fittest : 4
Generation : 339    Fittest : 4
Generation : 340    Fittest : 4
Generation : 341    Fittest : 4
Generation : 342    Fittest : 4
Generation : 343    Fittest : 4
Generation : 344    Fittest : 4
Generation : 345    Fittest : 6
Generation : 346    Fittest : 6
Generation : 347    Fittest : 4
Generation : 348    Fittest : 4
Generation : 349    Fittest : 4
Generation : 350    Fittest : 2
Generation : 351    Fittest : 2
Generation : 352    Fittest : 0
Generation : 353    Fittest : 2
Generation : 354    Fittest : 2
Generation : 355    Fittest : 4
Generation : 356    Fittest : 6
Generation : 357    Fittest : 6
Generation : 358    Fittest : 4
Generation : 359    Fittest : 4
Generation : 360    Fittest : 4
Generation : 361    Fittest : 4
Generation : 362    Fittest : 4
Generation : 363    Fittest : 6
Generation : 364    Fittest : 6
Generation : 365    Fittest : 6
Generation : 366    Fittest : 4
Generation : 367    Fittest : 4
Generation : 368    Fittest : 4
Generation : 369    Fittest : 4
Generation : 370    Fittest : 2
Generation : 371    Fittest : 4
Generation : 372    Fittest : 4
Generation : 373    Fittest : 2
Generation : 374    Fittest : 2
Generation : 375    Fittest : 4
Generation : 376    Fittest : 4
Generation : 377    Fittest : 4
Generation : 378    Fittest : 4
Generation : 379    Fittest : 4
Generation : 380    Fittest : 4
Generation : 381    Fittest : 4
Generation : 382    Fittest : 6
Generation : 383    Fittest : 6
Generation : 384    Fittest : 6
Generation : 385    Fittest : 4
Generation : 386    Fittest : 4
Generation : 387    Fittest : 6
Generation : 388    Fittest : 4
Generation : 389    Fittest : 4
Generation : 390    Fittest : 6
Generation : 391    Fittest : 6
Generation : 392    Fittest : 4
Generation : 393    Fittest : 4
Generation : 394    Fittest : 4
Generation : 395    Fittest : 4
Generation : 396    Fittest : 4
Generation : 397    Fittest : 4
Generation : 398    Fittest : 6
Generation : 399    Fittest : 6
Generation : 400    Fittest : 6
Generation : 401    Fittest : 6
Generation : 402    Fittest : 4
Generation : 403    Fittest : 6
Generation : 404    Fittest : 6
Generation : 405    Fittest : 4
Generation : 406    Fittest : 4
Generation : 407    Fittest : 4
Generation : 408    Fittest : 4
Generation : 409    Fittest : 2
Generation : 410    Fittest : 4
Generation : 411    Fittest : 4
Generation : 412    Fittest : 6
Generation : 413    Fittest : 6
Generation : 414    Fittest : 6
Generation : 415    Fittest : 6
Generation : 416    Fittest : 4
Generation : 417    Fittest : 4
Generation : 418    Fittest : 4
Generation : 419    Fittest : 4
Generation : 420    Fittest : 4
Generation : 421    Fittest : 4
Generation : 422    Fittest : 4
Generation : 423    Fittest : 2
Generation : 424    Fittest : 4
Generation : 425    Fittest : 4
Generation : 426    Fittest : 2
Generation : 427    Fittest : 2
Generation : 428    Fittest : 4
Generation : 429    Fittest : 6
Generation : 430    Fittest : 4
Generation : 431    Fittest : 4
Generation : 432    Fittest : 4
Generation : 433    Fittest : 4
Generation : 434    Fittest : 4
Generation : 435    Fittest : 6
Generation : 436    Fittest : 6
Generation : 437    Fittest : 6
Generation : 438    Fittest : 6
Generation : 439    Fittest : 4
Generation : 440    Fittest : 4
Generation : 441    Fittest : 4
Generation : 442    Fittest : 4
Generation : 443    Fittest : 4
Generation : 444    Fittest : 6
Generation : 445    Fittest : 6
Generation : 446    Fittest : 6
Generation : 447    Fittest : 6
Generation : 448    Fittest : 6
Generation : 449    Fittest : 6
Generation : 450    Fittest : 4
Generation : 451    Fittest : 4
Generation : 452    Fittest : 4
Generation : 453    Fittest : 2
Generation : 454    Fittest : 2
Generation : 455    Fittest : 2
Generation : 456    Fittest : 2
Generation : 457    Fittest : 4
Generation : 458    Fittest : 4
Generation : 459    Fittest : 4
Generation : 460    Fittest : 4
Generation : 461    Fittest : 6
Generation : 462    Fittest : 6
Generation : 463    Fittest : 6
Generation : 464    Fittest : 4
Generation : 465    Fittest : 6
Generation : 466    Fittest : 6
Generation : 467    Fittest : 4
Generation : 468    Fittest : 2
Generation : 469    Fittest : 4
Generation : 470    Fittest : 4
Generation : 471    Fittest : 2
Generation : 472    Fittest : 4
Generation : 473    Fittest : 6
Generation : 474    Fittest : 4
Generation : 475    Fittest : 4
Generation : 476    Fittest : 4
Generation : 477    Fittest : 4
Generation : 478    Fittest : 2
Generation : 479    Fittest : 2
Generation : 480    Fittest : 2
Generation : 481    Fittest : 2
Generation : 482    Fittest : 2
Generation : 483    Fittest : 2
Generation : 484    Fittest : 2
Generation : 485    Fittest : 2
Generation : 486    Fittest : 2
Generation : 487    Fittest : 2
Generation : 488    Fittest : 2
Generation : 489    Fittest : 2
Generation : 490    Fittest : 2
Generation : 491    Fittest : 2
Generation : 492    Fittest : 4
Generation : 493    Fittest : 4
Generation : 494    Fittest : 4
Generation : 495    Fittest : 4
Generation : 496    Fittest : 4
Generation : 497    Fittest : 6
Generation : 498    Fittest : 4
Generation : 499    Fittest : 4
Generation : 500    Fittest : 4
Generation : 501    Fittest : 4
Generation : 502    Fittest : 4
Generation : 503    Fittest : 4
Generation : 504    Fittest : 4
Generation : 505    Fittest : 4
Generation : 506    Fittest : 4
Generation : 507    Fittest : 4
Generation : 508    Fittest : 4
Generation : 509    Fittest : 2
Generation : 510    Fittest : 2
Generation : 511    Fittest : 2
Generation : 512    Fittest : 2
Generation : 513    Fittest : 4
Generation : 514    Fittest : 4
Generation : 515    Fittest : 2
Generation : 516    Fittest : 4
Generation : 517    Fittest : 4
Generation : 518    Fittest : 4
Generation : 519    Fittest : 4
Generation : 520    Fittest : 4
Generation : 521    Fittest : 2
Generation : 522    Fittest : 2
Generation : 523    Fittest : 2
Generation : 524    Fittest : 4
Generation : 525    Fittest : 6
Generation : 526    Fittest : 6
Generation : 527    Fittest : 4
Generation : 528    Fittest : 4
Generation : 529    Fittest : 6
Generation : 530    Fittest : 6
Generation : 531    Fittest : 4
Generation : 532    Fittest : 4
Generation : 533    Fittest : 4
Generation : 534    Fittest : 4
Generation : 535    Fittest : 2
Generation : 536    Fittest : 4
Generation : 537    Fittest : 4
Generation : 538    Fittest : 4
Generation : 539    Fittest : 4
Generation : 540    Fittest : 2
Generation : 541    Fittest : 2
Generation : 542    Fittest : 2
Generation : 543    Fittest : 2
Generation : 544    Fittest : 2
Generation : 545    Fittest : 4
Generation : 546    Fittest : 4
Generation : 547    Fittest : 6
Generation : 548    Fittest : 6
Generation : 549    Fittest : 6
Generation : 550    Fittest : 6
Generation : 551    Fittest : 6
Generation : 552    Fittest : 6
Generation : 553    Fittest : 4
Generation : 554    Fittest : 2
Generation : 555    Fittest : 4
Generation : 556    Fittest : 4
Generation : 557    Fittest : 4
Generation : 558    Fittest : 4
Generation : 559    Fittest : 6
Generation : 560    Fittest : 6
Generation : 561    Fittest : 6
Generation : 562    Fittest : 6
Generation : 563    Fittest : 4
Generation : 564    Fittest : 4
Generation : 565    Fittest : 2
Generation : 566    Fittest : 2
Generation : 567    Fittest : 2
Generation : 568    Fittest : 4
Generation : 569    Fittest : 4
Generation : 570    Fittest : 4
Generation : 571    Fittest : 4
Generation : 572    Fittest : 4
Generation : 573    Fittest : 4
Generation : 574    Fittest : 4
Generation : 575    Fittest : 4
Generation : 576    Fittest : 4
Generation : 577    Fittest : 6
Generation : 578    Fittest : 4
Generation : 579    Fittest : 4
Generation : 580    Fittest : 6
Generation : 581    Fittest : 6
Generation : 582    Fittest : 4
Generation : 583    Fittest : 4
Generation : 584    Fittest : 2
Generation : 585    Fittest : 4
Generation : 586    Fittest : 4
Generation : 587    Fittest : 4
Generation : 588    Fittest : 4
Generation : 589    Fittest : 4
Generation : 590    Fittest : 4
Generation : 591    Fittest : 6
Generation : 592    Fittest : 6
Generation : 593    Fittest : 4
Generation : 594    Fittest : 4
Generation : 595    Fittest : 4
Generation : 596    Fittest : 4
Generation : 597    Fittest : 4
Generation : 598    Fittest : 4
Generation : 599    Fittest : 4
Generation : 600    Fittest : 6
Generation : 601    Fittest : 4
Generation : 602    Fittest : 4
Generation : 603    Fittest : 6
Generation : 604    Fittest : 6
Generation : 605    Fittest : 8
Solution found in generation 605
Fitness :8
Genes :
11111111
*/
