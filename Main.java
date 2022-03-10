import java.util.*;

public class Main {
    static int n,m,s,f;

    static void DFS2(Pair<Integer, Integer> pair, HashMap<Pair<Integer, Integer>, Boolean> map, ArrayList<Integer> symbols, int[][] matr) {
        map.replace(pair, true);
        for (int i = 0; i < m; i++) {
            if(map.get(pair) == false && pair.getNode1() != pair.getNode2()) {
                symbols.add(i);
                Pair<Integer, Integer> pereche = new Pair<>(matr[pair.getNode1()][i], matr[pair.getNode2()][i]);
                DFS2(pereche, map, symbols,matr);
            }
        }
    }
     static void DFS(int node, Boolean[] visited, int[][] matr) {
        visited[node] = true;
        for(int i=0; i < m;i++) {
            if (!visited[matr[node][i]]) {
                DFS(matr[node][i], visited, matr);
            }
        }
    }
    public static void main(String[] args) {
        int i,j;
        //citire date de intrare
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        s = scanner.nextInt();
        f = scanner.nextInt();
        int[][] matr = new int[n][m];
        for(i = 0; i < n; i++) {
            for(j = 0; j < m; j++) {
                matr[i][j] = scanner.nextInt();
            }
        }
        int[] initState = new int[s];
        int[] finalState = new int[f];
        for(i =0; i <s; i++) {
            initState[i] = scanner.nextInt();
        }
        for(i =0; i <f; i++) {
            finalState[i] = scanner.nextInt();
        }
        // citire problema din argument
        String task = args[0];
        // functie de DFS

        // vedem tipul de problema
        if(task.equalsIgnoreCase("accessible")) {
            Boolean[] accessible = new Boolean[n];
            Arrays.fill(accessible, Boolean.FALSE);
            for (i = 0; i < s; i ++) {
                accessible[initState[i]] = true;
                Boolean[] visited = new Boolean[n];
                Arrays.fill(visited, Boolean.FALSE);
                DFS(initState[i],visited,matr);

                for (j = 0; j < n; j++) {
                    if (visited[j]) {
                        accessible[j] = true;
                    }
                }
            }
            for(i=0; i<n;i++) {
                if(accessible[i]) {
                    System.out.println(i);
                }
            }
        } else if(task.equalsIgnoreCase("productive")) {
            Boolean[] productive = new Boolean[n];
            Arrays.fill(productive, Boolean.FALSE);
            for (i = 0; i < n; i ++) {
                Boolean[] visited = new Boolean[n];
                Arrays.fill(visited, Boolean.FALSE);
                DFS(i,visited,matr);

                for (j = 0; j < f; j++) {
                    if (visited[finalState[j]]) {
                        productive[i] = true;
                    }
                }
            }
            for(i=0; i<n;i++) {
                if(productive[i]) {
                    System.out.println(i);
                }
            }
        } else if(task.equalsIgnoreCase("useful")) {
            Boolean[] a = new Boolean[n];
            Arrays.fill(a, Boolean.FALSE);
            Boolean[] p = new Boolean[n];
            Arrays.fill(p, Boolean.FALSE);

            for (i = 0; i < s; i ++) {
                a[initState[i]] = true;
                Boolean[] visited = new Boolean[n];
                Arrays.fill(visited, Boolean.FALSE);
                DFS(initState[i],visited,matr);
                for (j = 0; j < n; j++) {
                    if (visited[j]) {
                        a[j] = true;
                    }
                }
            }

            for (i = 0; i < n; i ++) {
                Boolean[] visited = new Boolean[n];
                Arrays.fill(visited, Boolean.FALSE);
                DFS(i, visited, matr);
                for (j = 0; j < f; j++) {
                    if (visited[finalState[j]]) {
                        p[i] = true;
                    }
                }
            }

            for (i = 0; i < n; i++) {
                if (a[i] && p[i]) {
                    System.out.println(i);
                }
            }
        } else if(task.equalsIgnoreCase("synchronize")) {
            Map<Pair<Integer, Integer>, Boolean> map = new HashMap<>();
           for(i = 0; i < n - 1;i++) {
               for(j= i; j < n;j++) {
                   Pair<Integer, Integer> pair = new Pair<>(i,j);
                   map.put(pair, false);
               }
           }
           ArrayList<Integer> symbols = new ArrayList<>();
           Pair<Integer, Integer> fisrtPair = new Pair<>(0,1);
           DFS2(fisrtPair, (HashMap<Pair<Integer, Integer>, Boolean>) map, symbols, matr);
           for(i = 0; i < symbols.size(); i++) {
               System.out.println(symbols.get(i));
           }
        }
    }
}
