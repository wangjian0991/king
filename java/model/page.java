package model;

/**
 * 使用时只需设置count和current. 若未设置current,默认0
 * 注意先设置count,不然下一页会计算出错//不必要了,计算了两次
 * @author wangjian
 *
 */
public class page {
	private int count=0;
	private int pages=0;
	private int current=0;
	private int next=0;
	private int last=0;
	private int size = 15;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		if (this.pages == 0) {
			this.pages = this.count / this.size;
			this.pages+=this.count % this.size == 0 ? 0	: 1;
		}
		if(this.current==this.pages)
			this.next=this.current;
		else
			this.next=this.current+1;
		if(this.current<=0)
			this.last=0;
		else
			this.last=this.current-1;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
		if(this.current==this.pages)
			this.next=this.current;
		else
			this.next=this.current+1;
		if(this.current<=0)
			this.last=0;
		else
			this.last=this.current-1;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getLast() {
		return last;
	}

	public void setLast(int last) {
		this.last = last;
	}

}
