package model;

import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ShopProgram {

	private Employee empleadoRaiz;
	private Client first;
	private ArrayList<Client> na ;
	public ArrayList<Employee> na2 ;

	/**
	 * @param empleadoRaiz
	 * @param first
	 */
	public ShopProgram() {
		Employee empleadoRaiz = new Employee(null, null,null,true);
		this.first = first;
		na = new ArrayList<Client>();
		na2 = new ArrayList<Employee>();
	}

	public String getNameEmployee() {
		return empleadoRaiz.getName();
	}

	public String getIdEmployee() {
		return empleadoRaiz.getId();
	}

	public Boolean getEmpl() {
		return empleadoRaiz.isEmpleado();
	}


	/**
	 * @return the empleadoRaiz
	 */
	public Employee getEmpleadoRaiz() {
		return empleadoRaiz;
	}


	/**
	 * @param empleadoRaiz the empleadoRaiz to set
	 */
	public void setEmpleadoRaiz(Employee empleadoRaiz) {
		this.empleadoRaiz = empleadoRaiz;
	}




	/**
	 * @return the na
	 */
	public ArrayList<Client> getNa() {
		ArrayList<Client>ar= new ArrayList<>();
		if(first != null) {
			Client act = first;
			while(act != null){
				ar.add(act);
				act = act.getNext();

			}

		}
		return ar;
	}




	/**
	 * @param na the na to set
	 */
	public void setNa(ArrayList<Client> na) {
		this.na = na;
	}



	public void addHumanTree(String name , String id,String phone)throws RepetitiveException {
		Employee nuevo = new Employee(name,id,phone,true);
		if(empleadoRaiz == null) {
			empleadoRaiz = nuevo;
			na2.add(nuevo);
		}
		else {
			Employee aux = empleadoRaiz;
			Employee padre;
			boolean bandera = true;
			while(bandera) {
				padre = aux;
				if( nuevo.compare(nuevo, aux)== -1) {
					aux = aux.getIzq();
					if(aux == null) {
						padre.setIzq(nuevo);
						bandera = false;
						na2.add(nuevo);
					}
				}else if( nuevo.compare(nuevo, aux)== 1)  {
					aux = aux.getDer();
					if(aux== null) {
						padre.setDer(nuevo);
						bandera = false;
						na2.add(nuevo);
					}
				}
				else if( nuevo.compare(nuevo, aux)== 0) {
					throw new RepetitiveException("The Employee Alrady Exist!"); 
				}
			}
		}

	}


	public void sh() {
		orderById();
		for (int i = 0; i <= na2.size()-1; i++) {
			System.out.println(na2.get(i).getName() +" "+na2.get(i).getId());
		}
	}

	public String inOrderName(Employee node) {
		String returnString = "";
		if (node != null) {
			returnString += " "+ inOrderName(node.getIzq());
			returnString += node.getName();
			returnString += inOrderName(node.getDer()) + " ";

		}
		return returnString;
	}

//	public String inOrder(Employee raiz) {
//		return raiz.inOrder();
//	}
	public String posOrder(Employee raiz) {
		String posOrder =raiz.posOrder(raiz);
		return posOrder;
	}

	public String preOrder(Employee raiz) {
		return raiz.preOrder(raiz);
	}
	
	
	public ArrayList<Employee> getNa2(Employee raiz){
		ArrayList<Employee> ar = new ArrayList<>();
		for (int i = 0; i <= na2.size()-1; i++) {
			ar.add(na2.get(i));
		}

		return ar;
	}

	public Employee searchEmployee(String id) {
		Employee act = null;

		for (int i = 0; i < na2.size(); i++) {
			if(getNa2(empleadoRaiz).get(i).getId().equals(id)) {
				act = getNa2(empleadoRaiz).get(i);
			}

		}
		return act;
	}

	public Employee findBinaryEmployee(String id){
		ArrayList<Employee>emp = orderById();
		boolean finded = false;
		Employee client=null;
		int start = 0;
		int end = emp.size()-1;
		while(start <= end && !finded) {
			int middle = (start + end)/2;
			if(emp.get(middle).getId().equals(id)) {
				client = emp.get(middle);

				finded = true;
			}
			else if(emp.get(middle).getId().compareTo(id) < 0) {
				end = middle - 1;
			}
			else {
				start = middle +1;

			}
		}
		return client;
	}
	public ArrayList<Employee> orderById(){
		ArrayList<Employee> arr = na2;
		for(int i = 1; i < arr.size(); i++) {
			for(int j = i; j >0; j--) {
				if(arr.get(j-1).compareTo(arr.get(j)) > 0) {
					Employee aux = arr.get(j);
					arr.set(j, arr.get(j-1));
					arr.set(j-1, aux);
				}
			}
		}
		return arr;
	}
	
	public ArrayList<Employee> orderByIdEmployeeMenorToBig(){
		ArrayList<Employee> arr = na2;
		for(int i = 1; i < arr.size(); i++) {
			for(int j = i; j >0; j--) {
				if(Integer.parseInt(arr.get(j-1).getId())>Integer.parseInt(arr.get(j).getId() ) ){
					Employee aux = arr.get(j);
					arr.set(j, arr.get(j-1));
					arr.set(j-1, aux);
				}
			}
		}
		return arr;
	}

	public void sh2() {
		ArrayList<Employee> arr=orderById();
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i).getName()+i);
		}
	}


	public void addClientToEmployee(String id, String idClient,String name,String phone) {
		searchEmployee(id).addClient(name, id, phone);
	}

	public Employee findingTheEmployeeOfTheMonth() {
		Employee mayor = null;
		ArrayList<Employee> emp = na2;
		for (int i = 0; i < emp.size()-1; i++) {
			if(emp.get(i).getNumClients()> emp.get(i+1).getNumClients()) {
				mayor = emp.get(i);
			}else if(emp.get(i).getNumClients()< emp.get(i+1).getNumClients()) {
				mayor = emp.get(i+1);
			}

			else {
				mayor=emp.get(i);
			}
		}
		return mayor;
	}
	
	
	public String[] getArrPosOrder() {
		String [] arrPosOrder= empleadoRaiz.posOrder(empleadoRaiz).split(" ");
		return arrPosOrder;
	}
	
	public ArrayList<Employee> getPosOrder(){
		ArrayList<Employee>arr = new ArrayList<>();
		return empleadoRaiz.posOrder2(empleadoRaiz, arr);
	}
	
	public ArrayList<Employee> getPreOrder(){
		ArrayList<Employee>arr = new ArrayList<>();
		return empleadoRaiz.preOrder2(empleadoRaiz, arr);
	}
	public ArrayList<Employee> getInOrder(){
		ArrayList<Employee>arr = new ArrayList<>();
		return empleadoRaiz.inOrder2(empleadoRaiz, arr);
	}
	

	//Clientes


	public boolean addCliente(String name , String id,String phone) throws RepetitiveException {
		Client c = new Client(name,id,phone,false);
		boolean added = false;
		Client actual = first;
		if(actual == null) {
			added = true;
			first = c;
			first.setNext(null);
			first.setPrev(null);
		}else if(!sameCliente(c)) {
			while(actual!=null && !added) {
				if(actual.getNext()==null) {
					actual.setNext(c);
					actual.getNext().setPrev(actual);
					added = true;
				}
				actual = actual.getNext();
			}
		}else {
			throw new RepetitiveException("The Client Alrady Exist!"); 
		}
		return added;
	}

	public boolean sameCliente(Client c) {
		boolean same = false;
		Client actual = first;
		while(actual != null && !same) {
			if(actual.compareTo(c)==0) {
				same = true;
			}
			actual = actual.getNext();
		}
		return same;
	}


	



	public String ShowClients() {
		String msg = "";
		if(first != null) {
			Client act = first;
			while(act != null) {

				msg +=  act.getName()+" ";

				act = act.getNext();

			} 

		}
		return msg;
	}
	public void ShowClients2() {
		ArrayList <Client>ar=getNa();
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i).getName()+" "+ i);
		}

	}

	public Client findClient(String id) {
		Client e = null;
		Client actual = first;
		boolean finded = false;
		while(actual != null && !finded) {
			if(actual.getId().equals(id)) {
				e = actual;
				finded = true;
			}
			actual = actual.getNext();
		}
		return e;
	}

	public Client findBinaryClient(String id){
		ArrayList<Client>emp = orderByIdClients();
		boolean finded = false;
		Client client=null;
		int start = 0;
		int end = emp.size()-1;
		while(start <= end && !finded) {
			int middle = (start + end)/2;
			if(emp.get(middle).getId().equals(id)) {
				client = emp.get(middle);

				finded = true;
			}
			else if(emp.get(middle).getId().compareTo(id) < 0) {
				end = middle - 1;
			}
			else {
				start = middle +1;

			}
		}
		return client;
	}
	public ArrayList<Client> orderByIdClients(){
		ArrayList<Client> arr = getNa();
		for(int i = 1; i < arr.size(); i++) {
			for(int j = i; j >0; j--) {
				if(arr.get(j-1).compareTo(arr.get(j)) > 0) {
					Client aux = arr.get(j);
					arr.set(j, arr.get(j-1));
					arr.set(j-1, aux);
				}
			}
		}
		return arr;
	}




	public void addProduct(String name, double cost, String id) {
		Product p = new Product(name,cost);
		findClient(id).addProducto(p);
	}



//		public String[] names (){
//			String[] ar = ShowClients().split(" ");
//			return ar;
//			
//		}
//		public String[] ids() {
//			String[] ar = ShowClients2().split(" ");
//			return ar;
//		}




}
