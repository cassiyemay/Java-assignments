package impl;

import core.Position;

public class ProperBinaryTree<T> extends AbstractBinaryTree<T> {
	public ProperBinaryTree() {
		root = new Node(null, null, null, null);
	}
	
	public void expandExternal(Position<T> p) {
		if (isInternal(p)) throw  new InvalidPositionException();
		
		Node node = (Node) p;
		node.left = new Node(node, null, null, null);
		node.right = new Node(node, null, null, null);
		size += 2;
	}

	public void collapseInternal(Position<T> p) {
		// Throw exception if node p is external or node p has two internal children
		if (isExternal(p) || (isInternal(left(p)) && isInternal(right(p)))) 
			throw new InvalidPositionException();
		
		Node node = (Node) p;
		node.element = null;
		if (isInternal(left(p))) {
			node.right.parent = null;
			node.right.element = null;
			node.right = null;
			if (isRoot(node)) {
				root = node.left;
				
				node.left.parent = null;
				node.left = null;
			} else {
				if (node.parent.left == node) {
					node.parent.left = node.left;
				} else {
					node.parent.right = node.left;
				}
				node.left.parent = node.parent;
			}
		} else if (isInternal(right(p))) {
			node.left.parent = null;
			node.left.element = null;
			node.left= null;
			if (isRoot(node)) {
				root = node.right;
				
				node.right.parent = null;
				node.right = null;
			} else {
				if (node.parent.left == node) {
					node.parent.left = node.right;
				} else {
					node.parent.right = node.right;
				}
				node.right.parent = node.parent;
			}
		} else {
			// Remove 2 children...
			node.left.element = null;
			node.right.element = null;
			node.left.parent = null;
			node.right.parent = null;
			node.left = null;
			node.right = null;
		}
		size -= 2;
	}
	
	public static void main(String[] args) {
		ProperBinaryTree<String> tree = new ProperBinaryTree<String>();
		tree.expandExternal(tree.root());
		tree.replace(tree.root(), "Happy");
		System.out.println(tree);

		tree.expandExternal(tree.left(tree.root()));
		tree.replace(tree.left(tree.root()), "Goes");
		System.out.println(tree);

		tree.expandExternal(tree.right(tree.root()));
		tree.replace(tree.right(tree.root()), "Lucky");
		System.out.println(tree);
		
		tree.collapseInternal(tree.right(tree.root()));
		System.out.println(tree);
		
		tree.collapseInternal(tree.root());
		System.out.println(tree);
	}
}
