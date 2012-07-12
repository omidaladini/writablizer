import org.apache.hadoop.io.Text

sealed trait StringIdentity {
  def value: String
  def textw : Text = new Text(value)
}

object StringIdentity {

  def apply(a: => String): StringIdentity = new StringIdentity {
    lazy val value = a
  }

  def unapply(v: StringIdentity): Option[String] = Some(v.value)
}

trait StringIdentitys {
  implicit def mkIdentity(x: => String): StringIdentity = StringIdentity(x)
  implicit def unMkIdentity[A](x: StringIdentity): String = x.value
}

