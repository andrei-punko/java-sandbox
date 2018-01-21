package by.andd3dfx.interview.yandex;

import java.util.ArrayList;
import java.util.List;

/*
Дан класс Node:
class Node {
	int value;
	Node left, right;
}

Реализовать алгоритм обхода бинарного дерева из нод, чтобы в итоге развернуть его в список
Исходно - дана корневая нода
 */
public class BinaryTree {

    public static class Node {
        int value;
        Node left, right;
    }

    public List<Integer> treeToList(Node node) {
        List<Integer> result = new ArrayList<>();
        walk(node, result);
        return result;
    }

    void walk(Node node, List<Integer> list) {
        if (node == null) {
            return;
        }

        walk(node.left, list);
        list.add(node.value);
        walk(node.right, list);
    }
}
