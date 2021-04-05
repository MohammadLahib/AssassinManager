
import java.util.ArrayList;

public class AssassinManager {
    private AssassinNode frontKill; // the front kill ring(use it to create the kill ring)
    private AssassinNode frontgrave;  // the front graveyard(use to create the grave yard)

    public AssassinManager(ArrayList<String> names){

        if (names.size() ==  0 || names == null)  // checking if the list is empty or has a size of zero
            throw new IllegalArgumentException("list is empty or has a size of 0");// throw an exception if its null or size 0
        for (int i = 0; i < names.size(); i++){
            AssassinNode currNode = new AssassinNode((names.get(i)));  // looped over the list and created a node for each name
            if (frontKill == null){
                frontKill = currNode;   // if the fontkill is null ===> empty then we need to assign the first node to it
            }else{
                AssassinNode nextNode = frontKill; // since we are looping so frontkill will not be null at i = 1 so we  need to create another node that keep the names.get(i)
            while (nextNode.next != null){ // these while loop to place the currNode 
                nextNode = nextNode.next; // while kill ring not empty ===> go to the next
            }
            nextNode.next = currNode; // storing currNode in the null position(Last Node)
            }
        }
    }

    public void printKillRing(){ // print who is stalking to whom
        AssassinNode node = frontKill;
        while (node != null && node.next != null){
            System.out.println(node.name + " is stalking " + node.next.name);  // looping until we reach last node 
            node = node.next;
        }
        System.out.println(node.name + " is stalking " + frontKill.name);  // linking last node with the first node 
    }

    public void printGraveyard(){    // print who  killed eric 
        AssassinNode grave = frontgrave;
        while (grave != null){
            if(grave.next == null){
                System.out.println(grave.name + " was killed by " + grave.killer);  // grave.killer is pointing the killer of grave.name
            }else{
                System.out.println(grave.name + " was killed by " + grave.killer);
            }
            grave= grave.next;
            
        }
    }

    // checking if KillRingConatains name or not
    public boolean killRingContains(String name){
        // name = name.toLowerCase();
        boolean bool = false;
        AssassinNode currNode = frontKill;

        while(currNode != null ){
            if (currNode.name.equalsIgnoreCase(name)){  // comparing with ignoring all the cases 
                bool = true;
                break;
            }
            currNode = currNode.next;
        }
        return bool;
    }

    // checking if graveyard contains name
    public boolean graveyardContains(String name){
        // name = name.toLowerCase();
        boolean bool = false;
        AssassinNode currNode = frontgrave;
        
        while (currNode != null){
            if (currNode.name.equalsIgnoreCase(name)){   // comparing ignoring all the cases
                bool = true;
                break;
            }
            currNode = currNode.next;
        }
        return bool;
    }

    // checking if the game overed
    // when we have just one person in the game 
    public boolean isGameOver(){
        return (frontKill.next == null);
    }

    // returing the name of the winner
    public String winner(){
        String result = "";
        if (isGameOver()){
            result += frontKill.name;
        }else{
            result = null;
        }
        return result;
    }

    

    // kill the victim and move him/her from the killring to the graveyard
    public void kill(String name){
        AssassinNode currNode = frontKill;
        if(isGameOver()){
            throw new IllegalStateException("Game is Over"); // check if the game is over
        }
        if(!killRingContains(name)){
            throw new IllegalArgumentException("name is not in the kill ring"); // if the name not in the KillRing
        }else{


            
            while(currNode!=null && !currNode.name.equalsIgnoreCase(name)) {  // search for the name
                currNode = currNode.next;
            }
            AssassinNode dead = frontgrave;  // 
            if(frontKill.name.equalsIgnoreCase(name)){ // if name at the beginning 
                frontgrave= frontKill; // place on the graveyard
                frontKill= frontgrave.next; // remove it from frontkill
            }
            else{ // if name of the victim not at the beginning 
                frontgrave = currNode.next; // place on the graveyard
                currNode.next = currNode.next.next;// remove it from frontkill
            }
            // adding to the graveyard
                frontgrave.next = dead;
                frontgrave.killer = currNode.name;
            }
        }
}
