import org.apache.hadoop.io.IntWritable

sealed trait IntIdentity {
  def value: Int
  def intw : IntWritable = new IntWritable(value)
}

object IntIdentity {

  def apply(a: => Int): IntIdentity = new IntIdentity {
    lazy val value = a
  }

  def unapply(v: IntIdentity): Option[Int] = Some(v.value)
}

trait IntIdentitys {
  implicit def mkIdentity(x: => Int): IntIdentity = IntIdentity(x)
  implicit def unMkIdentity[A](x: IntIdentity): Int = x.value
}

