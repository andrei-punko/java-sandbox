package by.andd3dfx.interview.ya;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BinaryTreeTest {

    private BinaryTree binaryTree;

    @Before
    public void setUp() {
        binaryTree = new BinaryTree();
    }

    @Test
    public void walk() {
        BinaryTree.Node rootNode = createNode(10);
        rootNode.left = createNode(8);
        rootNode.left.left = createNode(5);
        rootNode.left.right = createNode(9);

        rootNode.right = createNode(11);
        rootNode.right.left = createNode(10);
        rootNode.right.right = createNode(16);

        List<Integer> result = binaryTree.treeToList(rootNode);

        assertThat("Wrong size of result list", result.size(), is(7));
        int[] expectedList = {5, 8, 9, 10, 10, 11, 16};
        for (int i = 0; i < expectedList.length; i++) {
            assertThat("Wrong element", result.get(i), is(expectedList[i]));
        }
    }

    private BinaryTree.Node createNode(int value) {
        BinaryTree.Node result = new BinaryTree.Node();
        result.value = value;
        return result;
    }
}
