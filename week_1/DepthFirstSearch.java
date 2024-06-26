package week_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DepthFirstSearch {

	static class Vertex {
		public int id;
		public HashSet<Vertex> adjecentVertices = new HashSet<Vertex>();
		public boolean visited;

		public Vertex(int id) {
			super();
			this.id = id;
		}

		public void addAdjecentVertex(Vertex vertex) {
			adjecentVertices.add(vertex);
		}

		public int getDegree() {
			return adjecentVertices.size();
		}

		@Override
		public String toString() {
			return id + " ";
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Vertex) {
				return ((Vertex) obj).id == id;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return ((Integer) id).hashCode();
		}
	}
}
