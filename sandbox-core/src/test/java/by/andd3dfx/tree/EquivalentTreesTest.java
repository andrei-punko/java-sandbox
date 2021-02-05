package by.andd3dfx.tree;

import org.junit.Before;
import org.junit.Test;

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
    public void findEquivalentSubtrees() {
        EquivalentTrees.Node node = new EquivalentTrees.Node('A');
        node.left = new EquivalentTrees.Node('B');
        assertThat(equivalentTrees.findEquivalentSubtrees(node), is(nullValue()));

        node.right = new EquivalentTrees.Node('C');
        assertThat(equivalentTrees.findEquivalentSubtrees(node), is(nullValue()));
    }
}