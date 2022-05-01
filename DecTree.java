package DecardTree;
import java.util.Random;

     public class DecTree implements SearchTree {

         private static final Long SEED = 123L;
         private static final Random RAND = new Random(SEED);

         private Integer x;
         private Integer y;
         private DecTree left;
         private DecTree right;

         public DecTree(int x, int y, DecTree left, DecTree right) {
             this.x = x;
             this.y = y;
             this.left = left;
             this.right = right;
         }

         public DecTree(int x, int y) {
             this(x, y, null, null);
         }

         public DecTree(int x) {
             this(x, RAND.nextInt());
         }


         public DecTree merge(DecTree L, DecTree R) {
             if (L == null) return R;
             if (R == null) return L;

             if (L.y > R.y) {
                 return new DecTree(L.x, L.y, L.left, merge(L.right, R));
             } else /* L.y <= R.y */ {
                 return new DecTree(R.x, R.y, merge(L, R.left), R.right);
             }
         }


         public DecTree[] split(int x) {
             DecTree newTree = null;
             DecTree L, R;
             if (this.x < x) {
                 if (right == null) {
                     R = null;
                 } else {
                     DecTree[] tmp = right.split(x);
                     newTree = tmp[0];
                     R = tmp[1];
                 }
                 L = new DecTree(this.x, this.y, left, newTree);
             } else {
                 if (left == null) {
                     L = null;
                 } else {
                     DecTree[] tmp = left.split(x);
                     L = tmp[0];
                     newTree = tmp[1];
                 }
                 R = new DecTree(this.x, this.y, newTree, right);
             }
             return new DecTree[]{L, R};
         }

         @Override
         public DecTree add(int x) {
             DecTree[] t = split(x);
             DecTree l = t[0];
             DecTree r = t[1];
             DecTree m = new DecTree(x);
             return merge(merge(l, m), r);
         }

         @Override
         public DecTree remove(int x) {
             /*
              * На каждом шаге рекурсии спрашиваем, не совпало ли значение
              * Если совпало, просто выкидываем текущую вершину и
              * сливаем вместе её детей
              */
             if (this.x == x)
                 return merge(left, right);

             /*
              * Если искомое значение меньше, чем текущее,
              * то отправляемся в левое дерево, просим его удалить х
              */
             if (this.x > x && left != null)
                 left = left.remove(x);

             /*
              * Если искомое значение больше, чем текущее,
              * то отправляемся в правое дерево, просим его удалить х
              */
             if (this.x < x && right != null)
                 right = right.remove(x);

             /*
              * Либо кто-то из детей перезаписал left/right, удалив значение,
              * либо никто его не нашёл и структура осталась без изменений.
              * Возвращаем её в любом случае
              */
             return this;
         }

         @Override
         public boolean contains(int x) {

             /*
              * На каждом шаге рекурсии спрашиваем, не совпало ли значение
              * Если совпало, возвращаем истину
              */
             if (this.x == x) return true;

             /*
              * Если искомое значение меньше текущего, то даём такой же ответ,
              * какой ответ даст левое поддерево о наличии в нём такого значения
              */
             if (this.x > x && left != null) return left.contains(x);

             /*
              * Если искомое значение больше текущего, то даём такой же ответ,
              * какой ответ даст правое поддерево о наличии в нём такого значения
              */
             if (this.x < x && right != null) return right.contains(x);

             /*
              * Если не зашли ни в один if, значит требуемого ребёнка просто нет.
              * Возвращаем false
              */
             return false;
         }
     }



