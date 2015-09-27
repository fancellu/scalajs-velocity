package com.felstar.scalajs.velocity

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.dom._

import js.annotation.JSName

    // remember, browser must support Promises, either natively or 3rd party lib, like bluebird or when
  @js.native
  trait Promise extends js.Object {   
    @JSName("then")    
    def andThen(onFulfilled: js.Function,onRejected:js.Function):Promise=js.native   
    @JSName("then")    
     def andThen(onFulfilled: js.Function):Promise=js.native           
    @JSName("catch")    
    def andCatch(onRejected:js.Function):Promise=js.native 
  }

 @js.native
 object Velocity extends js.Object{
     def apply(el:js.Any,properties: js.Any, duration: js.Any = ???, easing: String = ???, complete: js.Function = ???): Promise = js.native
     def apply(el:js.Any,properties: js.Any, options:js.Any): Promise = js.native
     def apply(el:js.Any,properties: js.Any): Promise = js.native          
     def hook(el:js.Any,prop: String,value:String): js.Any = js.native
     def hook(el:js.Any,prop: String): String = js.native
     val Easings:js.Dynamic=js.native
     var mock:js.Any=js.native
     val defaults:js.Dynamic=js.native
     def RunSequence(seq:js.Any):js.Any=js.native
     def RegisterEffect(name:String, options:js.Any):js.Any=js.native
   }