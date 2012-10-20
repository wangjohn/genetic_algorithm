package random_sampling;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomSample {
    
    public static int getUniform(int lowerBound, int upperBound){
        Random rand = new Random();
        return lowerBound + rand.nextInt(upperBound - lowerBound);
    }
    
    /**
     * Las Vegas algorithm for getting a random sample of m items. If m > n, then we return the entire set of n.
     * @param items The items to sample from
     * @param m The number of items to return
     * @return A randomly selected subset of m items from the list of items
     * @throws InvalidProbabilityArgumentException 
     */
    public static <T> Set <T> randomSample(List <T> items, int m) throws InvalidProbabilityArgumentException{
        int n = items.size();
        Set<T> resultSet = new HashSet<T>();
        if (m < 0){
            throw new InvalidProbabilityArgumentException("The number of items to select must be non-negative.");
        } else if (m >= n){
            // Just return all the items if m >= n
            for (T item : items){
                resultSet.add(item);
            }
            return resultSet;
        }
    
        if (m > n/2){
            // If we have more than n/2 items, perform reverse operation on those not 
            // included in the random sample
            Set<T> notInSet = randomSample(items, n-m);
            for (T item : items){
                if (!notInSet.contains(item)){
                    resultSet.add(item);
                }
            }
        } else {
            Random rand = new Random();
            int counter = 0;
            int nextRandomIndex;
            // We continue pulling them into our set until we have reached m samples
            while (counter < m){
                nextRandomIndex = rand.nextInt();
                if (!resultSet.contains(nextRandomIndex)){
                    resultSet.add(items.get(nextRandomIndex));
                }
            }
        }
        return resultSet;
    }
}
