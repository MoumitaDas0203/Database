import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



 class hash {

    /**
     * Java tutorial to read CSV file in java.
     * Problem extension; Comma exists in data to be parsed
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        //File location that contains data
        final String fileLoc = "C:/Users/Moumi/Desktop/moumita.csv";
       /*
        ArrayList<ArrayList> a=getlnameListFromFile();
        System.out.println(a.toString());
        */
        /*
        	setup();
        	
        	BufferedReader file = new BufferedReader(new FileReader(fileLoc));
        	String record = null;
        	record=file.readLine();
        	while((record = file.readLine()) != null)
        	
        	{
        	
        		readFromFile(record);
        	    //file.close();
        }
        
    	
    	//DisplayId(0);*/
    	/*
    	//DisplayId(306);
    	
    	//printlname("Butt");
    	//printID("525");
    	//printstate("LA");
        */
        
        
    }
     
       public void insertcommand(String a) throws ClassNotFoundException, IOException{
    	   //a="'5023','Jam1es','Butt','Benton, John B Jr','6649 N Blue Gum St','New Orleans','Orleans','LA','70116','504-621-8927','504-845-1427','jbutt@gmail.com','http://www.bentonjohnbjr.com'";
           a=a.replace("','","|");
           a=a.replace(",", "~");
           a=a.replace("|", ",");
           a=a.replace("'", "");
           
           readFromFile(a);
       }
    
    
    
      public static void readFromFile( String data ) throws IOException, ClassNotFoundException
      {
           	
    	  	System.out.println(data);
        	RandomAccessFile bin = new RandomAccessFile("C:/Users/Moumi/Desktop/test1.txt", "rw");
    		bin.seek(bin.length());
    		
    		long Pos =bin.length();
    		data=remCommaFmData(data);
    		//createIdIndex(data, bin.length());
    		String[] recordData = data.split(",");
    		int k=0;
    		//System.out.println("aaaaaa");
    		for(String s : recordData){
    			s=s.replace("~", ",");
    			//System.out.println(".."+s);
    			if(k==0){
    				
    				int a=Integer.parseInt(s);
    				bin.writeChar(a);
    				bin.writeChars(":");
    				//System.out.println(s);
    				addIdIndex(data,Pos);
    				addlnameIndex(data,Pos);
    				addstateIndex(data,Pos);
    				//DisplayId();
    				
    			}
    			else{
    				//s.replace("~",",");
    				bin.writeChars(s);
    				bin.writeChars(":");
    				
    			}
    			k++;
    			
    			//System.out.println(s);
    		}
    		bin.writeChars(";");
    		bin.close();
        		}
      // function to create an object file of type arrayList for ID column
      
     private static ArrayList<ArrayList> getIdListFromFile() throws  FileNotFoundException,IOException,ClassNotFoundException
     {
  	  	
  		
  		ObjectInputStream IdFile = new ObjectInputStream(new FileInputStream("C:/Users/Moumi/Desktop/id.ndx"));
  		ArrayList<ArrayList> IdArrayList=(ArrayList<ArrayList>) IdFile.readObject();
  		IdFile.close();
  		ObjectOutputStream IdFile1 = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
  		IdFile1.writeObject(IdArrayList);
  		IdFile1.close();
  		return IdArrayList;
  	}
      
      //Function to add the ID column to the index file object
      
     private static void addIdIndex(String data, long Pos) throws IOException, ClassNotFoundException {
		    
    	    data = remCommaFmData(data);
			data=data.replace("\"", "");
    	 
    	 	ArrayList<ArrayList> IdArrayList=getIdListFromFile();				
			//ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
			
			String[] recordData = data.split(",");//splitting it word wise
			int p=Integer.parseInt(recordData[0]);
			if(!doesIndexExists(p)){
		    //ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
			ArrayList obj=new ArrayList();
			obj.add(p);
			obj.add(Pos);
			//System.out.println(idobj.get(1));
			IdArrayList.add(obj);// Storing the Arraylist the to its object
			//System.out.println(IdArrayList.toString());
			ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
			FileOutput.writeObject(IdArrayList);// Storing the Arraylist object to the file object
			
			FileOutput.flush();
			FileOutput.close();
			}
			
			
			
	}
     
     
     //to know if the id enetered already exists or  not
     
    
	private static int IntegerParseInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}



	public static boolean doesIndexExists(int index) throws FileNotFoundException, ClassNotFoundException, IOException{
 		
 		ArrayList<ArrayList> IdArrayList = getIdListFromFile();
 		ArrayList<Integer> IdList=new ArrayList<Integer>();
 		
 		for(ArrayList i:IdArrayList){
 			//IdList.add.(Integer.parseInt((String) i.get(0)));

 			IdList.add((Integer) i.get(0));
 		}
 		
 		if(IdList.contains(index)){
 			return true;
 		}else{
 			return false;
 		}
 	}
	
	
	
      
      
   
    

   



	private static String remCommaFmData(String record) {
         String output = record;

         //Create a pattern which detects comma in data
        Pattern p = Pattern.compile("\"[a-zA-Z0-9 ]+[,][ a-zA-Z0-9]+\"");

        //Match the pattern on the record
        Matcher matcher = p.matcher(output);

        //For each pattern match replace , with !@
        while(matcher.find()){
            String replacement = matcher.group().replaceAll(",", "~");
            output = output.replaceAll( matcher.group(), replacement);
        }

        //return record with , replaced with !@ in data
        return output;
    }

	
	
	
	
	

	public static void setup() throws IOException, ClassNotFoundException{
		
		
		ArrayList<ArrayList> IdArrayList = new ArrayList();
		ObjectOutputStream IdFile= new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
		IdFile.writeObject(IdArrayList);
		IdFile.close();
		ArrayList<ArrayList> lnameIndexArray = new ArrayList();
		ObjectOutputStream lnameFile = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/lname.ndx"));
		lnameFile.writeObject(lnameIndexArray);
		lnameFile.close();
		ArrayList<ArrayList> stateIndexArray = new ArrayList();
		ObjectOutputStream stateIndexFile = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/state.ndx"));
		stateIndexFile.writeObject(stateIndexArray);
		stateIndexFile.close();
	}
    /**
     
     * Function to read file.
     *
     */
    private static String getData(String fileLoc) throws IOException {
        FileReader fr = new FileReader(fileLoc);
        BufferedReader br = new BufferedReader(fr);

        String data = br.readLine();
        StringBuffer buff = new StringBuffer();

        while(data != null){
            buff.append(data + System.getProperty("line.separator"));
            data=br.readLine();
        }

        //br.close();
        //fr.close();

        return buff.toString();
    }
 
 
 
 // function to create an object file of type arrayList for  last name column
 
 private static ArrayList<ArrayList> getlnameListFromFile() throws  FileNotFoundException,IOException,ClassNotFoundException
 {
	  
			
		ObjectInputStream lnameFile = new ObjectInputStream(new FileInputStream("C:/Users/Moumi/Desktop/lname.ndx"));
		ArrayList<ArrayList> lnameIndexArray=(ArrayList<ArrayList>) lnameFile.readObject();
		lnameFile.close();
		ObjectOutputStream lnameFile1 = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/lname.ndx"));
		lnameFile1.writeObject(lnameIndexArray);
		lnameFile1.flush();
		lnameFile1.close();
		return lnameIndexArray;
	}
 
 

 
 //Adding last name to the index file
 
 private static void addlnameIndex(String data, long Pos) throws IOException, ClassNotFoundException {
	    
	 	    data = remCommaFmData(data);
		data=data.replace("\"", "");
	 
	 	ArrayList<ArrayList> lnameIndexArray=getlnameListFromFile();				
		//ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
		
		String[] recordData = data.split(",");//splitting it word wise
		//int p=Integer.parseInt(recordData[0]);
		//int rec= Integer.parseInt(recordData[2]);
		if(!doeslnameExists(recordData[2])){
	    //ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
		ArrayList  obj=new ArrayList();
		obj.add(recordData[2]);
		obj.add(Pos);
		//System.out.println(idobj.get(1));
		lnameIndexArray.add(obj);// Storing the arraylist the to its object
		//System.out.println(lnameIndexArray.toString());
		//System.out.println(lnameIndexArray.toString());
		ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/lname.ndx"));
		FileOutput.writeObject(lnameIndexArray);// Storing the Arraylist object to the file object
		
		FileOutput.flush();
		FileOutput.close();
		}
		
		else 
		{
			
			ArrayList a=new ArrayList();
			for(ArrayList i:lnameIndexArray)
			{
				a.add(i.get(0));
			}
			//System.out.println(a.toString());
			int p=a.indexOf(recordData[1]);
			//System.out.println(p);
			lnameIndexArray.get(p).add(Pos);
			ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/lname.ndx"));
			FileOutput.writeObject(lnameIndexArray);// Storing the Arraylist object to the file object
			
			FileOutput.flush();
			FileOutput.close();
		}
			
 }
		
		public static boolean doeslnameExists(String p) throws FileNotFoundException, ClassNotFoundException, IOException{
	 		
			
			 
	 		ArrayList<ArrayList> lnameIndexArray = getIdListFromFile();
	 		
	 		ArrayList<String> lnameList=new ArrayList<String>();
	 		
	 		for(ArrayList i:lnameIndexArray){
	 			//IdList.add.(Integer.parseInt((String) i.get(0)));

	 			lnameList.add(i.get(0).toString());
	 		}
	 		
	 		if(lnameList.contains(p)){
	 			return true;
	 		}else{
	 			return false;
	 		}
	 	}
	
		
		/* for State */
		
		// function to create an object file of type arrayList for  last name column
		 
		 private static ArrayList<ArrayList> getStateListFromFile() throws  FileNotFoundException,IOException,ClassNotFoundException
		 {
			  	
				
				ObjectInputStream stateFile = new ObjectInputStream(new FileInputStream("C:/Users/Moumi/Desktop/state.ndx"));
				ArrayList<ArrayList> stateIndexArray=(ArrayList<ArrayList>) stateFile.readObject();
				stateFile.close();
				ObjectOutputStream stateFile1 = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/state.ndx"));
				stateFile1.writeObject(stateIndexArray);
				stateFile1.flush();
				stateFile1.close();
				return stateIndexArray;
			}

		 
		 //Adding last name to the index file
		 
		 private static void addstateIndex(String data, long Pos) throws IOException, ClassNotFoundException {
			    
			    data = remCommaFmData(data);
				data=data.replace("\"", "");
			 
			 	ArrayList<ArrayList> stateIndexArray=getStateListFromFile();				
				//ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
				
				String[] recordData = data.split(",");//splitting it word wise
				if(!statedoesnotExists(recordData[7])){
			    //ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
				ArrayList obj=new ArrayList();
				//System.out.println("jajaja");
				//System.out.println(recordData[7]);
				obj.add(recordData[7]);
				obj.add(Pos);
				//System.out.println(idobj.get(1));
				stateIndexArray.add(obj);// Storing the arraylist the to its object
				//System.out.println(stateIndexArray.toString());
				//System.out.println(stateIndexArray.toString());
				ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/state.ndx"));
				FileOutput.writeObject(stateIndexArray);// Storing the Arraylist object to the file object
				
				FileOutput.flush();
				FileOutput.close();
				}
				
				
				else 
				{
					ArrayList a=new ArrayList();
					for(ArrayList i:stateIndexArray)
					{
						a.add(i.get(0));
					}
					//System.out.println(a.toString());
					int p=a.indexOf(recordData[7]);
					//System.out.println(p);
					stateIndexArray.get(p).add(Pos);
					ObjectOutputStream FileOutput = new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/state.ndx"));
					FileOutput.writeObject(stateIndexArray);// Storing the Arraylist object to the file object
					
					FileOutput.flush();
					FileOutput.close();
					
				}
		 }
				
				public static boolean statedoesnotExists(String p) throws FileNotFoundException, ClassNotFoundException, IOException{
			 		
			 		ArrayList<ArrayList> stateIndexArray = getStateListFromFile();
			 		ArrayList<String> stateList=new ArrayList<String>();
			 		
			 		for(ArrayList i:stateIndexArray){
			 			//IdList.add.(Integer.parseInt((String) i.get(0)));

			 			stateList.add(i.get(0).toString());
			 		}
			 		if(stateList.contains(p)){
			 			return true;
			 		}else{
			 			return false;
			 		}
			 
				
			}
				
				
				/* Select query: finding the position of the record by lname in  arraylist object */
				
				public static void printlname(String val2) throws FileNotFoundException, ClassNotFoundException, IOException
			    {
				int index1;
				long index2;
				
				
				ArrayList<ArrayList> lnameArrayList = new ArrayList();
				lnameArrayList=getlnameListFromFile();			
				
				ArrayList lnameList=new ArrayList();
				//ObjectOutputStream IdFile= new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
				for(ArrayList lname:lnameArrayList){
		 			

		 			lnameList.add(lname.get(0));
		 		}
				
				
				
				index1=lnameList.indexOf((val2));
				if(index1!=-1)
				{
					
					for ( int j=1;j<lnameArrayList.get(index1).size();j++)
					{
					index2=(long) lnameArrayList.get(index1).get(j);
					Displaylname(index2);
				}
				}
				else
				{
					System.out.println("No record exists");
				}
					
				
				}
			
			
			
			/* Select query: reading record from binary file by lname*/
			
			 public static void Displaylname(long index) throws IOException, ClassNotFoundException
			 
			 {   
				 String rec=""; int id=0;char c;
				 RandomAccessFile bin = new RandomAccessFile("C:/Users/Moumi/Desktop/test1.txt", "rw"); 
				 bin.seek(index);
				 
				 id=bin.readUnsignedShort();// reading the values of the first column which is integer from the binary file
				 rec=rec+id;
				 
				 c= bin.readChar();
				 rec=rec+c;
				 
				 while(c!=';')
				 {
					 rec=rec+c;
					 c=bin.readChar();
				 }
				 
				 System.out.println(rec);
				
				 
			 }	
	
			
			
	  



				
				/* Select query: finding the position of the record by ID in  arraylist object */
				
				
				public static void printID(String val2) throws FileNotFoundException, ClassNotFoundException, IOException
				    {
					int index1;
					long index2;
					
					
					ArrayList<ArrayList> IdArrayList = new ArrayList();
					IdArrayList=getIdListFromFile();
					
					
					ArrayList IdList=new ArrayList();
					//ObjectOutputStream IdFile= new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
					for(ArrayList Id:IdArrayList){
			 			

			 			IdList.add(Id.get(0));
			 		}
					
					index1=IdList.indexOf(Integer.parseInt(val2));
					if(index1!=-1)
					{
						index2=(long) IdArrayList.get(index1).get(1);
						DisplayId(index2);
					}
					else
					{
						System.out.println("No record exists");
					}
						
					
					}
				
				
				
				/* Select query: reading record from binary file by id */
				
				 public static void DisplayId(long index) throws IOException, ClassNotFoundException
				 
				 {   
					 String rec=""; int id=0;char c;
					 RandomAccessFile bin = new RandomAccessFile("C:/Users/Moumi/Desktop/test1.txt", "rw"); 
					 bin.seek(index);
					 
					 id=bin.readUnsignedShort();// reading the values of the first column which is integer from the binary file
					 rec=rec+id;
					 
					 c= bin.readChar();
					 rec=rec+c;
					 
					 while(c!=';')
					 {
						 rec=rec+c;
						 c=bin.readChar();
					 }
					 
					 System.out.println(rec);
					
					 
				 }	

				 
				 /* Select query: finding the position of the record by lname in  arraylist object */
					
					public static void printstate(String val2) throws FileNotFoundException, ClassNotFoundException, IOException
				    {
					int index1;
					long index2;
					
					
					ArrayList<ArrayList> stateArrayList = new ArrayList();
					stateArrayList=getStateListFromFile();			
					
					ArrayList stateList=new ArrayList();
					//ObjectOutputStream IdFile= new ObjectOutputStream(new FileOutputStream("C:/Users/Moumi/Desktop/id.ndx"));
					for(ArrayList state: stateArrayList){
			 			

			 			stateList.add(state.get(0));
			 		}
					
					
					
					index1=stateList.indexOf((val2));
					if(index1!=-1)
					{
						
						for ( int j=1;j<stateArrayList.get(index1).size();j++)
						{
						index2=(long) stateArrayList.get(index1).get(j);
						Displaylname(index2);
					}
					}
					else
					{
						System.out.println("No record exists");
					}
						
					
					}
				
				
				
				/* Select query: reading record from binary file by lname*/
				
				 public static void Displaystate(long index) throws IOException, ClassNotFoundException
				 
				 {   
					 String rec=""; int id=0;char c;
					 RandomAccessFile bin = new RandomAccessFile("C:/Users/Moumi/Desktop/test1.txt", "rw"); 
					 bin.seek(index);
					 
					 id=bin.readUnsignedShort();// reading the values of the first column which is integer from the binary file
					 rec=rec+id;
					 
					 c= bin.readChar();
					 rec=rec+c;
					 
					 while(c!=';')
					 {
						 rec=rec+c;
						 c=bin.readChar();
					 }
					 
					 System.out.println(rec);
					
					 
				 }	
		
				
				
		  
				 
 }
				
				
		  
 

