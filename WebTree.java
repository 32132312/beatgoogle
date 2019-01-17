import java.io.IOException;
import java.util.ArrayList;

public class WebTree {
	public WebNode root;
	StringBuilder sb = new StringBuilder();

	public WebTree(WebPage rootPage) {
		this.root = new WebNode(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(WebNode startNode, ArrayList<Keyword> keywords) throws IOException {
		for (WebNode child : startNode.children) {
			setPostOrderScore(child, keywords);
		}
		startNode.setNodeScore(keywords);
	}

	public void printTree() {
		printTree(root);
		System.out.println(sb.toString());
	}

	private void printTree(WebNode startNode) {
		// using getDepth()
		// •Œ\t¡Y±∆
		// if (startNode.children.isEmpty()) {
		// sb.append("(" + startNode.webPage.name + "," + startNode.nodeScore + ")" +
		// "\n");
		// } else {
		// sb.append("(" + startNode.webPage.name + "," + startNode.nodeScore + "\n");
		// for (WebNode child : startNode.children) {
		// for (int i = 1; i < child.getDepth(); i++) {
		// sb.append("\t");
		// }
		// printTree(child);
		// }
		// for (int i = 1; i < startNode.getDepth(); i++) {
		// sb.append("\t");
		// }
		// sb.append(")"+"\n");
		// }
		sb.append("(" + startNode.webPage.name + ", " + startNode.nodeScore);
		for (WebNode child : startNode.children) {
			sb.append("\n");
			for (int i = 1; i < child.getDepth(); i++) {
				sb.append("\t");
			}
			printTree(child);
		}
		if (!startNode.children.isEmpty()) {
			sb.append("\n");
			for (int i = 1; i < startNode.getDepth(); i++) {
				sb.append("\t");
			}
		}
		sb.append(")");
	}
}
