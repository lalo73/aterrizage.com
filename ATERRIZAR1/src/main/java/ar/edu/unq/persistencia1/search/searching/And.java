package ar.edu.unq.persistencia1.search.searching;


public class And extends SearchCriteria{
	private SearchCriteria left;
	private SearchCriteria right;

	public And(){}

	public SearchCriteria getLeft() {
		return left;
	}

	public void setLeft(SearchCriteria left) {
		this.left = left;
	}

	public SearchCriteria getRight() {
		return right;
	}

	public void setRight(SearchCriteria right) {
		this.right = right;
	}
}
