
public class PathList {
	private Destination head;
	private Destination tail;
	private int count =0;
	
	private class Destination{
		private String latitude;	//위도, 33~43
		private String longtitude;	//경도,	124~132
		private String destName;	//여행지 이름
		
		//다음 노드를 가리킴
		private Destination next;	// 다음 여행지 정보
		
		//Constructor
		Destination(String destName, String latitude, String longtitude){
			this.next = null;
			this.destName = destName;
			this.latitude = latitude;
			this.longtitude = longtitude;
		}
		
	}
	
	//출발지 노드 추가
	public void setStartDestination(String destName, String latitude, String longtitude) {
		Destination firstDestination = new Destination(destName, latitude, longtitude);
		firstDestination.next = head;	//원래의 첫 노드를 다음 2번째 노드로 지정
		head = firstDestination;
		count++;
		if(head.next == null){
	        tail = head;
	    }
	}
	
	//도착지 노드 추가
	public void setArrivalDestination(String destName, String latitude, String longtitude) {
		Destination lastDestination = new Destination(destName, latitude, longtitude);
		if(count == 0)
			setStartDestination(destName, latitude, longtitude);
		else {
			tail.next = lastDestination;
			tail = lastDestination;
			count++;	
		}
	}
	
	//Head 설정
	public void setHead(Destination head) {
		this.head = head;
	}
	
	//Tail 설정
	public void setTail(Destination tail) {
		this.tail = tail;
	}
	
	//여행지 생성
	Destination createDestination(int number) {
		Destination newDestination = head;
		for(int i=0; i< number; i++)
			newDestination = newDestination.next;
		return newDestination;
	}

	//여행지를 원하는 곳에 추가
	public void addDestination(int number, String destName, String latitude, String longtitude) {
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
	
	//여행 경로 출력
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
	
	//출발지 삭제
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
	
	//여행지의 개수
	public int getcount() {
		return count;
	}
	
	//몇번째 방문할 여행지가 무엇인지 얻기
	public Object getDestinationName(int number) {
		//Destination dest = createDestination(number-1);
		Destination dest = createDestination(number);
		return dest.destName;
	}
	
	public String getDestinationLat(int number) {
		Destination dest= createDestination(number);
		return dest.latitude;
	}
	
	public String getDestinationLon(int number) {
		Destination dest= createDestination(number);
		return dest.longtitude;
	}
	
	//여행지가 몇 번째 방문할지 조회
	public int getDestOrder(String destName) {
		Destination dest = head;
		int order = 1;
		
		while(!dest.destName.equals(destName)) {
			dest = dest.next;
			order++;
			
			//끝에 노드 일때(도착지 일때)
			if(dest == null)
				return -1;
		}
		return order;
	}
}
