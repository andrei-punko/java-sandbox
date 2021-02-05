package by.andd3dfx.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class EquivalentTreesTest {

    private EquivalentTrees equivalentTrees;

    @Before
    public void setUp() throws Exception {
        equivalentTrees = new EquivalentTrees();
    }

    @Test
    public void findEquivalentSubtreesForNull() {
        assertThat(equivalentTrees.findEquivalentSubtrees(null), is(nullValue()));
    }

    @Test
    public void findEquivalentSubtreesForOneNode() {
        EquivalentTrees.Node node = new EquivalentTrees.Node('A');
        assertThat(equivalentTrees.findEquivalentSubtrees(node), is(nullValue()));
    }

    @Test
    public void findEquivalentSubtreesWhenNoCandidates() {
        EquivalentTrees.Node node = new EquivalentTrees.Node('A');
        node.left = new EquivalentTrees.Node('B');
        assertThat(equivalentTrees.findEquivalentSubtrees(node), is(nullValue()));
    }

    @Test
    public void findEquivalentSubtreesWhenOneCandidate() {
        EquivalentTrees.Node node = new EquivalentTrees.Node('A');
        node.left = new EquivalentTrees.Node('B');
        node.right = new EquivalentTrees.Node('B');

        List<EquivalentTrees.Node> result = equivalentTrees.findEquivalentSubtrees(node);

        assertThat(result.size(), is(2));
        assertThat(result, hasItems(node.left, node.right));
    }

    @Test
    public void findEquivalentSubtreesWhenManyCandidatesExist() {
        EquivalentTrees.Node root = buildNode('A');
        root.left = buildNode('B');
        root.right = buildNode('C');

        root.left.left = buildNode('E');
        root.left.left.left = buildNode('D');

        root.right.left = buildNode('D');
        root.right.right = buildNode('E');
        root.right.right.left = buildNode('E');
        root.right.right.right = buildNode('D');

        List<EquivalentTrees.Node> result = equivalentTrees.findEquivalentSubtrees(root);

        assertThat("Two nodes expected", result.size(), is(2));
        assertThat("Wrong pair of nodes", result, hasItems(root.left, root.right));
    }

    private EquivalentTrees.Node buildNode(char value) {
        return new EquivalentTrees.Node(value);
    }
}
