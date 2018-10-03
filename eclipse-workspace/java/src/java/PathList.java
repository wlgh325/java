
public class PathList {
	private Destination head;
	private Destination tail;
	private int count =0;
	
	private class Destination{
		private float latitude;	//����, 33~43
		private float longtitude;	//�浵,	124~132
		private String destName;	//������ �̸�
		
		//���� ��带 ����Ŵ
		private Destination next;	// ���� ������ ����
		
		//Constructor
		Destination(String destName, float latitude, float longtitude){
			this.next = null;
			this.destName = destName;
			this.latitude = latitude;
			this.longtitude = longtitude;
		}
		
	}
	//����� ��� �߰�
	public void setStartDestination(String destName, float latitude, float longtitude) {
		Destination firstDestination = new Destination(destName, latitude, longtitude);
		firstDestination.next = head;	//������ ù ��带 ���� 2��° ���� ����
		head = firstDestination;
		count++;
		if(head.next == null){
	        tail = head;
	    }
	}
	
	//������ ��� �߰�
	public void setArrivalDestination(String destName, float latitude, float longtitude) {
		Destination lastDestination = new Destination(destName, latitude, longtitude);
		if(count == 0)
			setStartDestination(destName, latitude, longtitude);
		else {
			tail.next = lastDestination;
			tail = lastDestination;
			count++;	
		}
	}
	
	//Head ����
	public void setHead(Destination head) {
		this.head = head;
	}
	
	//Tail ����
	public void setTail(Destination tail) {
		this.tail = tail;
	}
	
	//������ ����
	Destination createDestination(int number) {
		Destination newDestination = head;
		for(int i=0; i< number; i++)
			newDestination = newDestination.next;
		return newDestination;
	}

	//�������� ���ϴ� ���� �߰�
	public void addDestination(int number, String destName, float latitude, float longtitude) {
		if(number==1)
			setStartDestination(destName, latitude, longtitude);
		else {
			Destination temp1 = createDestination(number-2);
			Destination temp2 = temp1.next;
			
			Destination newDestination = new Destination(destName, latitude, longtitude);
			temp1.next = newDestination;
			newDestination.next = temp2;
			if(temp2 == null)
				tail = newDestination;
			count++;
			
		}
	}
	
	//���� ��� ���
	public void printTravelRoute() {
		if(head == null)
			System.out.println("No Route!!");
		
		Destination dest = head;
		String route = "Route: ";
		
		while(dest.next != null) {
			route += dest.destName + " -> ";
			dest = dest.next;
		}
		route += dest.destName;
		System.out.println(route);
	}
	
	//����� ����
	public Object deleteStartDestination() {
		Destination temp = head;
		head = temp.next;
		
		Object delDestination = temp.destName;
		temp = null;
		count--;
		
		return delDestination;
	}
	
	public Object delete(int number){
	    if(number == 1)
	        return deleteStartDestination();
	    
	    Destination temp = createDestination(number-2);
	    Destination todoDeleted = temp.next;
	    
	    temp.next = temp.next.next;
	    
	    Object deleteData = todoDeleted.destName;
	    if(todoDeleted == tail){
	        tail = temp;
	    }
	    
	    todoDeleted = null; 
	    count--;
	    return deleteData;
	}
	
	//�������� ����
	public int getcount() {
		return count;
	}
	
	//���° �湮�� �������� �������� ���
	public Object getDestination(int number) {
		Destination dest = createDestination(number-1);
		return dest.destName;
	}
	
	//�������� �� ��° �湮���� ��ȸ
	public int getDestOrder(String destName) {
		Destination dest = head;
		int order = 1;
		
		while(!dest.destName.equals(destName)) {
			dest = dest.next;
			order++;
			
			//���� ��� �϶�(������ �϶�)
			if(dest == null)
				return -1;
		}
		return order;
	}
}