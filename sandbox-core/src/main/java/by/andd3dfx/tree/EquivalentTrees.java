package by.andd3dfx.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Дано бинарное дерево с выделенным корнем, в каждой вершине которого записано по одной букве A-Z.
 * Две вершины считаются эквивалентными, если поддеревья этих вершин содержат одинаковое множество (т.е. без учета частот) букв.
 * Нужно найти две эквивалентные вершины с максимальным суммарным размеров поддеревьев.
 * <p>
 * public class Node {
 * char value;  // [A-Z]
 * Node left;
 * Node right;
 * };
 */
public class EquivalentTrees {

    public static class Node {
        char value;  // [A-Z]
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
        }

        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public List<Node> findEquivalentSubtrees(Node root) {
        if (root == null) {
            return null;
        }

        // Build Node -> Vocabulary map
        Map<Node, Set<Character>> node2Voc = new HashMap<>();
        buildNode2Voc(root, node2Voc);

        // Build Set<Character> -> List<Node> map
        Map<Set<Character>, List<Node>> voc2Nodes = new HashMap<>();
        for (Node node : node2Voc.keySet()) {
            Set<Character> value = node2Voc.get(node);

            if (!voc2Nodes.containsKey(value)) {
                voc2Nodes.put(value, new ArrayList<>());
            }
            voc2Nodes.get(value).add(node);
        }

        // Found equivalent nodes
        voc2Nodes = voc2Nodes.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= 2)
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue()
                ));
        // Only absent sets with at least 2 related nodes remain

        if (voc2Nodes.isEmpty()) {
            return null;
        }

        // Build Node->nodeSize map
        Map<Node, Integer> node2Size = new HashMap<>();
        buildNode2Size(root, node2Size);

        // TODO: UNFINISHED: in each list of voc2Nodes.value - find two nodes with max sum size of subtree. after that - find pair with max sum

        // Until task not finished - just return first element
        return (List<Node>) voc2Nodes.values().toArray()[0];
    }

    private Set<Character> buildNode2Voc(Node node, Map<Node, Set<Character>> node2Voc) {
        if (!node2Voc.containsKey(node)) {
            node2Voc.put(node, new HashSet());
        }
        node2Voc.get(node).add(node.value);

        if (node.left != null) {
            node2Voc.get(node).addAll(buildNode2Voc(node.left, node2Voc));
        }
        if (node.right != null) {
            node2Voc.get(node).addAll(buildNode2Voc(node.right, node2Voc));
        }

        return node2Voc.get(node);
    }

    private int buildNode2Size(Node node, Map<Node, Integer> node2Size) {
        if (!node2Size.containsKey(node)) {
            node2Size.put(node, 0);
        }
        node2Size.put(node, node2Size.get(node) + 1);

        if (node.left != null) {
            node2Size.put(node, node2Size.get(node) + buildNode2Size(node.left, node2Size));
        }
        if (node.right != null) {
            node2Size.put(node, node2Size.get(node) + buildNode2Size(node.right, node2Size));
        }

        return node2Size.get(node);
    }
}
