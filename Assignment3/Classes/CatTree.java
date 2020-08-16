package assignment3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CatTree implements Iterable<CatInfo>{
    public CatNode root;
    
    public CatTree(CatInfo c) {
        this.root = new CatNode(c);
    }
    
    private CatTree(CatNode c) {
        this.root = c;
    }
    
    public void addCat(CatInfo c)
    {
        this.root = root.addCat(new CatNode(c));
    }
    
    public void removeCat(CatInfo c)
    {
        this.root = root.removeCat(c);
    }
    
    public int mostSenior()
    {
        return root.mostSenior();
    }
    
    public int fluffiest() {
        return root.fluffiest();
    }
    
    public CatInfo fluffiestFromMonth(int month) {
        return root.fluffiestFromMonth(month);
    }
    
    public int hiredFromMonths(int monthMin, int monthMax) {
        return root.hiredFromMonths(monthMin, monthMax);
    }
    
    public int[] costPlanning(int nbMonths) {
        return root.costPlanning(nbMonths);
    }
    
    public Iterator<CatInfo> iterator()
    {
        return new CatTreeIterator();
    }
    

//    private void inOrderTraversal(CatNode currentNode, ArrayList<CatInfo> list) { 
//      if (currentNode.senior != null) inOrderTraversal(currentNode.senior, list);
//      if (currentNode.same != null) inOrderTraversal(currentNode.same, list);
//      list.add(currentNode.data);
//      if (currentNode.junior != null) inOrderTraversal(currentNode.junior, list);
//    }
    
    class CatNode {
        
        CatInfo data;
        CatNode senior;
        CatNode same;
        CatNode junior;
        
        public CatNode(CatInfo data) {
            this.data = data;
            this.senior = null;
            this.same = null;
            this.junior = null;
        }

        public String toString() {
            String result = this.data.toString() + "\n";
            if (this.senior != null) {
                result += "more senior " + this.data.toString() + " :\n";
                result += this.senior.toString();
            }
            if (this.same != null) {
                result += "same seniority " + this.data.toString() + " :\n";
                result += this.same.toString();
            }
            if (this.junior != null) {
                result += "more junior " + this.data.toString() + " :\n";
                result += this.junior.toString();
            }
            return result;
        }
        
        
        public CatNode addCat(CatNode c) {
            // case where cat to add "c" is more senior
            if (c.data.monthHired < this.data.monthHired) {
                if (this.senior == null) {
                    this.senior = c;
                    return this;
                }
                this.senior = this.senior.addCat(c);
            }
            // case where cat to add "c" is more junior
            else if (c.data.monthHired > this.data.monthHired) {
                if (this.junior == null) {
                    this.junior = c;
                    return this;
                }
                this.junior = this.junior.addCat(c);

            }
            // case where cat to add "c" is equal to root; must be compared based on fur thickness
            else if (c.data.monthHired == this.data.monthHired) {
                if (c.data.furThickness > this.data.furThickness) { // cat is more fluffy than the current one
                    c.senior = this.senior;
                    c.junior = this.junior;
                    c.same = this;
                    this.senior = null;
                    this.junior = null;
                    return c;
                }
                else if ((c.data.furThickness < this.data.furThickness)) {
                    if (this.same == null) {
                        this.same = c;
                        return this;
                    }
                    this.same = this.same.addCat(c);
                }
            }
            else { // root is null; tree is empty therefore nothing can be added
                return null;
            }
            return this;
        }
        
        
        public CatNode removeCat(CatInfo c) { 
            // cat to remove has been found
            if (this.data.equals(c)) {
                // case where c.same == null and c.senior == null and c.junior != null: c.junior replaces c
                if ((this.same == null) && (this.senior == null) && (this.junior != null)) {
                    this.same = this.junior.same;
                    this.senior = this.junior.senior;
                    this.data = this.junior.data;
                    this.junior = this.junior.junior;
                    return this;
                }
                // case where c.same == null and c.senior != null: c.senior replaces c
                else if ((this.same == null) && (this.senior != null)) {
                    if (this.senior.junior != null) {
                        this.senior.junior.junior = this.junior;
                        this.junior = this.senior.junior;
                    }
                    else {
                        this.senior.junior = this.junior;
                    }
                    this.same = this.senior.same;
                    this.data = this.senior.data;
                    this.senior = this.senior.senior;
                    return this;
                }
                // case where c.same != null: c.same replaces c
                else {
                    this.same.senior = this.senior;
                    this.same.junior = this.junior;
                    this.data = this.same.data;
                    this.same = this.same.same;
                    return this;
                }
            }
            // traversing tree to find node
            // cat is more senior
            else if ((c.monthHired < this.data.monthHired) && (this.senior != null)) {
                this.senior = this.senior.removeCat(c);
            } 
            else if ((c.monthHired > this.data.monthHired) && (this.junior != null)) {
                this.junior = this.junior.removeCat(c);
            }
            else if ((c.monthHired == this.data.monthHired) && (c.furThickness < this.data.furThickness) && (this.same != null)) {
                this.same = this.same.removeCat(c);
            }
            // case where there's nothing to remove
            // root == null
            else if (root == null){
                return null;
            }
            return this; 
        }
        
        public int mostSenior() {
            try {
                if (this.senior == null) {
                    return this.data.monthHired;
                }
                else {
                    return this.senior.mostSenior();
                }
            }
            catch (Exception e) {
                System.out.println("Check your tree, perhaps it is empty!");
            }
            return this.senior.data.monthHired; 
        }
        
        public int fluffiest() { 
            int fluffiestCat = this.data.furThickness; 
            
            if (this.senior == null && this.junior == null) {
                return this.data.furThickness;
            }
            else if (this.senior != null) {
                fluffiestCat = Math.max(fluffiestCat, this.senior.fluffiest());
            }
            else if (this.junior != null) {
                fluffiestCat = Math.max(fluffiestCat, this.junior.fluffiest());
            }
            else {
                return -1;
            }
            return fluffiestCat;
        }
       
        
        // Another way to implement this method
//        public int hiredFromMonths(int monthMin, int monthMax) { 
//          int count = 0;
//          try {
//              ArrayList<CatInfo> cats = new ArrayList<CatInfo>();
//              inOrderTraversal(root, cats);
//              int start = binarySearch(cats, monthMin);
//                  
//              for (int i = start; i < cats.size(); i++) {
//                  if ((cats.get(i).monthHired >= monthMin) && (cats.get(i).monthHired <= monthMax)) {
//                          count++;
//                  }
//              }
//              return count;
//          }
//          catch (Exception e) {
//              System.out.println("Element not");
//          }
//            return count;
//        } 
        
        public int hiredFromMonths(int monthMin, int monthMax) {
            CatTree cats = new CatTree(this);
            int catsHired = 0;
            if (monthMin > monthMax) {
                return 0;
            }
            
            for (CatInfo c: cats) {
                if (c.monthHired >= monthMin && c.monthHired <= monthMax) {
                    catsHired++;
                }
            }
            return catsHired;
        } 
        
//        private int binarySearch(ArrayList<CatInfo> list, int elementToFind) {
//          int left = 0;
//          int right = list.size()-1;
//          
//          while(left <= right) {
//              int middle = (left+right)/2;
//              if (list.get(middle).monthHired == elementToFind) {
//                  return middle;
//              }
//              else {
//                  if (elementToFind < list.get(middle).monthHired) {
//                      right = middle-1;
//                  }
//                  else {
//                      left = middle+1;
//                  }
//              }
//          }
//          return -1; // if elementToFind is not in list
//        }
        
        
        public CatInfo fluffiestFromMonth(int month) {
            if (month == this.data.monthHired) {
                return this.data;
            }
            else if (month < this.data.monthHired && this.senior != null) {
                return this.senior.fluffiestFromMonth(month);
            }
            else if (month > this.data.monthHired && this.junior != null) {
                return this.junior.fluffiestFromMonth(month);
            } 
            else {
                return null;
            }
        }
        
        public int[] costPlanning(int nbMonths) {
            int[] monthlySpending = new int[nbMonths];
            CatTree cats = new CatTree(this);
            int counterAppointment = 243;

            for (CatInfo c: cats) {
                if (c.nextGroomingAppointment - counterAppointment < monthlySpending.length) {
                    monthlySpending[c.nextGroomingAppointment - counterAppointment] += c.expectedGroomingCost;
                }
                else {
                    continue;
                }
            }
            return monthlySpending; 
        }
    }
    
    private class CatTreeIterator implements Iterator<CatInfo> {
       ArrayList<CatInfo> cats = new ArrayList<CatInfo>(30);
       private int i = 0;
       
       public CatTreeIterator() {
            inOrderTraversal(root, cats);
       }
        
       public CatInfo next(){
           if ((cats.get(i)) != null) {
               return cats.get(i++);
           }
           else {
               throw new NoSuchElementException("No cat.");
           }
       }
        
       public boolean hasNext() {
           if (i < cats.size()) {
               return true;
           }
           return false;
           
       }
       
       private void inOrderTraversal(CatNode currentNode, ArrayList<CatInfo> list) { 
           if (currentNode.senior != null) inOrderTraversal(currentNode.senior, list);
           if (currentNode.same != null) inOrderTraversal(currentNode.same, list);
           list.add(currentNode.data);
           if (currentNode.junior != null) inOrderTraversal(currentNode.junior, list);
       }
   }
    
    
}

