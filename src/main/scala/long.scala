import org.apache.hadoop.io.LongWritable

sealed trait LongIdentity {
  def value: Long
  def longw : LongWritable = new LongWritable(value)
}

object LongIdentity {

  def apply(a: => Long): LongIdentity = new LongIdentity {
    lazy val value = a
  }

  def unapply(v: LongIdentity): Option[Long] = Some(v.value)
}

trait LongIdentitys {
  implicit def mkIdentity(x: => Long): LongIdentity = LongIdentity(x)
  implicit def unMkIdentity[A](x: LongIdentity): Long = x.value
}

