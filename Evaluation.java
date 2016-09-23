import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class Evaluation {
	private HashMap<String, vNode> nameVMap;
	private ArrayList<String> vNameList;
	
	public Evaluation(){
		nameVMap = new HashMap<String, vNode>();
		vNameList = new ArrayList<String>();
	}
	
	public void addFunction(String f){
		StringTokenizer st = new StringTokenizer(f, "=");
		String vNameL = st.nextToken();
		vNode vNodeL = null;
		if(nameVMap.containsKey(vNameL)){
			vNodeL = nameVMap.get(vNameL);
		} else {
			vNodeL = new vNode(vNameL);
			vNameList.add(vNameL);
			nameVMap.put(vNameL, vNodeL);
		}
		
		
		int l = f.indexOf("(");
		int r = f.indexOf(")");
		String ss = f.substring(l+1, r);
		if(ss.length() == 0){
			vNodeL.setStatus(1);
		} else {
			st = new StringTokenizer(ss, ",");
			while(st.hasMoreTokens()){
				String vNameR = st.nextToken();
				vNode nodeR = null;
				if(nameVMap.containsKey(vNameR)){
					nodeR = nameVMap.get(vNameR);
				} else {
					nodeR = new vNode(vNameR);
					vNameList.add(vNameR);
					nameVMap.put(vNameR, nodeR);
				}
				vNodeL.addAdj(nodeR);
				
			}
		}
	}
	
	public boolean isValid(){
		for(int i=0; i<vNameList.size(); i++){
			boolean valid = dfs(vNameList.get(i));
			if(!valid){
				return false;
			}
		}
		return true;
	}
	
	private boolean dfs(String name){
		
		vNode node = nameVMap.get(name); 
		
		if(node.visited){
			return false;
		}
		
		if(node.getStatus() == 1){
			return true;
		}
		
		node.visited = true;
		boolean ret = true;
		Iterator<vNode> it = node.adjV.iterator();
		if(!it.hasNext()){
			if(node.status == -1){
				ret = false;
			} else {
				ret = true;
			}
		} else {
			while(it.hasNext()){
				vNode adjNode = it.next();
				boolean cRet = dfs(adjNode.name);
				ret = ret&cRet;
				if(!ret){
					return false;
				}
			}
			
			node.setStatus(1);
		}
		node.visited = false;
		return ret;
	}
	
	
	public class vNode{
		public String name;
		private LinkedList<vNode> adjV;
		private int status; //-1, unsolved, 1, solved;
		public boolean visited;
		
		public vNode(String s){
			this.name = s;
			adjV = new LinkedList<vNode>();
			status = -1;
			visited = false;
		}
		
		public void addAdj(vNode node){
			adjV.add(node);
		}
		
		public int getStatus(){
			return status;
		}
		
		public void setStatus(int newStatus){
			this.status = newStatus;
		}
	}

	
	public static void main(String[] args) throws IOException{
		String path = "";
		String inputFile = path + "C-large-practice.in";

		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++){
			
			int N = Integer.parseInt(br.readLine());
			Evaluation eObj = new Evaluation();
			for(int j=0; j<N; j++){
				String f = br.readLine();
				eObj.addFunction(f);
			}
			Boolean ret = eObj.isValid();
			
			System.out.print("Case #" + i + ": ");
			if(ret){
				System.out.println("GOOD");
			} else {
				System.out.println("BAD");
			}
		}
	}
}
