package CodeDojo.CreateAFunnel;

import java.util.ArrayList;
import java.util.List;

class Funnel {
    Character[][] data = new Character[5][];
    private static class Node {
        private int rowIndex;
        private int columnIndex;

        public Node(int rowIndex, int columnIndex){
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

        public Character getValue(Funnel f){
            return f.data[this.rowIndex][this.columnIndex];
        }

        @Override
        public String toString(){
            return "("+this.rowIndex+","+this.columnIndex+")";
        }

        public Node getLeftChild() {
            return new Node(this.rowIndex + 1, this.columnIndex);
        }

        public Node getRightChild() {
            return new Node(this.rowIndex + 1, this.columnIndex + 1);
        }

        public void setValue(Character val, Funnel f) {
            f.data[this.rowIndex][this.columnIndex] = val;
        }
    }

    public Funnel() {
        init();
    }
    
    public void fill(char...args) {
        for (int index = 0; index < args.length; index++) {
            if(!add(args[index])){
                return;
            }
        }
    }
    
    private boolean add(Character c){
        for (int i = 0; i < data.length; i++) {
            Character[] level = data[i];
            for (int j = 0; j < level.length; j++) {
                if (level[j] == null){
                    level[j] = c;
                    return true;
                }
            }
        }
        return false;
    }

    public Character drip() {
        Character root = data[0][0];
        data[0][0] = null;
        fix(new Node(0, 0));
        return root;
    }

    private void fix(Node n){
        if ((n.getValue(this) != null) || (n.rowIndex + 1 >= data.length)){
            return;
        }

        Node leftChild = n.getLeftChild();
        Node rightChild = n.getRightChild();

        Node winner = getWinner(leftChild, rightChild);
        if (winner != null){
            Character val = winner.getValue(this);
            n.setValue(val, this);
            winner.setValue(null, this);
            fix(winner);
        }
    }

    private Node getWinner (Node left, Node right){
        Integer leftWeight = getWeight(left);
        Integer rightWeight = getWeight(right);
        if (leftWeight == 0 && rightWeight == 0){
            return null;
        }
        if (leftWeight >= rightWeight){
            return left;
        }
        return right;
    }

    private Integer getWeight(Node n){
        if (n.getValue(this) == null){
            return 0;
        }
        int weight = 1;
        List<Node> allChildren = this.getAllChildren(n);
        for (Node node : allChildren) {
            if (node.getValue(this) != null){
                weight++;
            }
        }    
        return weight;
    }

    private List<Node> getAllChildren(Node n){
        List<Node> nodes = new ArrayList<Node>();
        for (int i = n.rowIndex + 1; i < data.length; i++) {
            int depth = i - n.rowIndex;
            int colTerm = depth + n.columnIndex + 1;
            for (int j = n.columnIndex; j < colTerm; j++) {
                Node child = new Node(i, j);
                nodes.add(child);
            }
        }
        return nodes;
    }

    private void init(){
        for (int i = 0; i < data.length; i++) {
            Character[] level = new Character[i + 1];
            data[i] = level;
        }
    }
    
    @Override
    public String toString() {
        String stringified = "";
        String padding = "";
        for (int i = data.length - 1; i > -1 ; i--) {
            stringified += (padding +"\\");
            padding += " ";
            Character[] level = data[i];
            for (int j = 0; j < level.length; j++) {
                if (level[j] == null){
                    stringified += " ";
                } else {
                    stringified += level[j];
                }
                if(j + 1 < level.length){
                    stringified += " ";
                }
            }
            if(i == 0){
                stringified += "/";
            } else {
                stringified += "/\n";
            }
        }
        return stringified;
    }

    public static void main(String[] args){
        Funnel f = new Funnel();
        f.fill('5','8','3', '7', '9', '6');
        System.out.println(f.drip());
        System.out.println(f);
        System.out.println(f.drip());
        System.out.println(f);
        System.out.println(f.drip());
        System.out.println(f);
        System.out.println(f.drip());
        System.out.println(f);
        System.out.println(f.drip());
        System.out.println(f);
        System.out.println(f.drip());
        System.out.println(f);
    }
}