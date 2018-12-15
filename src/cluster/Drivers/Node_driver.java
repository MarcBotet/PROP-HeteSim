package cluster.Drivers;

import cluster.Nodes.Node;

public class Node_driver {

    public static void main(String[] args) {

        //Crea un objecte de la classe node.
        Node n = new Node("First", 1);

        //Escriu el Id: 1.
        System.out.println(n.getId());


        //Escriu el nom: First.
        System.out.println(n.getName());

        //Modifica el nom del parametre i l'escriu: Second.
        n.setName("Second");
        System.out.println(n.getName());

    }

}
