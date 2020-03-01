package com.example.kotlin.day06

/**
 * 在kotlin中，定义注解是通过 annotation class 注解名(参数列表) 的方式来声明一个注解的。
 * @Target 就是 目标注解，定义该注解可以声明在什么对象上面。静态值定义在 AnnatationTarget类中
 * @Retention 存在时期，表示当前的注解可以持续存在到什么时期。值定义在 AnnotationRetention类中
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class  UserInfoAnno(val name:String,val level:Int,val isVip :Boolean)


@UserInfoAnno("shui",4,false)
class QQSoftWare