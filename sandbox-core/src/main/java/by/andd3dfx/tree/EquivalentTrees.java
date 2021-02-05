package by.andd3dfx.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
 * Node[] FindEquivalentSubtrees(Node root);
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
    }

    public List<Node> findEquivalentSubtrees(Node root) {
        if (root == null) {
            return null;
        }

        // Node -> Vocabulary
        Map<Node, Set<Character>> node2Voc = new HashMap<>();
        buildNode2Voc(root, node2Voc);

        // Set<Character> -> List<Node>
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
        // Only absent sets with at least 2 related nodes

        if (voc2Nodes.isEmpty()) {
            return null;
        }

        // TODO: UNFINISHED - find nodes with max size of subtree

        // Until task not finished - just return first element
        Node nodes = voc2Nodes.keySet().toArray(new Node[0])[0];
        return voc2Nodes.get(nodes);
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
}
