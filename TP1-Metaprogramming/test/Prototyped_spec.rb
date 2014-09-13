require 'rspec'
require "TP1/Prototyped"
require "TP1/Metaprogramming"

describe 'Hierarchy Redirection' do

  before (:all) do
    @nuevoObjeto = TP::PrototypedObject.new
    @nuevoObjetoPrototipado = TP::PrototypedObject.new
  end

  it 'should add new properties' do
    @nuevoObjeto.set_property(:prop, 500)
    expect(@nuevoObjeto.prop).to eq(500)
  end

  it 'should edit properties correctly' do
    @nuevoObjeto.prop = 600
    expect(@nuevoObjeto.prop).to eq(600)
  end

  it 'should not let prototypes share states' do
    expect{@nuevoObjetoPrototipado.prop}.to raise_error(NoMethodError)
  end

  it 'should let edit properties from every instance independently' do
    @nuevoObjetoPrototipado.set_property(:prop, 1999)
    expect(@nuevoObjetoPrototipado.prop).to eq(1999)
  end

  it 'should set a new prototype' do

    @nuevoObjetoPrototipado.set_prototype(@nuevoObjeto)

    @nuevoObjeto.set_method(:devolver, lambda{50})

    expect(@nuevoObjetoPrototipado.devolver).to eq(50)

  end

  it 'Should let Singleton Modules find behaviour' do

    @previouslyProto = TP::PrototypedObject.new
    @newObject = TP::PrototypedObject.new
    @latelyProto = TP::PrototypedObject.new

    @previouslyProto.set_prototype(@newObject)

    @newObject.set_method(:a, lambda{50})
    expect(@newObject.singleton_module.method_defined?(:a)).to eq(true)

    @latelyProto.set_prototype(@newObject)

    expect(@previouslyProto.a).to eq(50)
    expect(@latelyProto.a).to eq(50)

  end

  it 'should redefine previous behaviour' do

    @nuevoObjetoPrototipado.define_singleton_method(:devolver, lambda{180})

    expect(@nuevoObjeto.devolver).to eq(50)
    expect(@nuevoObjetoPrototipado.devolver).to eq(180)

  end

  it 'should let share prototyped behaviour between instances of different classes' do

    @anObject = Object.new
    @anObject.extend TP::Prototyped

    @aClass = Class.new
    @aClass.extend TP::Prototyped

    @anObject.set_method(:metodin, lambda{70})

    @aClass.set_prototype(@anObject)

    expect(@aClass.metodin).to eq(70)

  end

  it 'should prototyped find methods with super' do
    @anObject = Object.new
    @anObject.extend TP::Prototyped

    @aClass = Class.new
    @aClass.extend TP::Prototyped


    @anObject.set_property(:times, 0)
    @aClass.set_property(:times, 0)

    @anObject.set_method(:metodin, proc{if(@times == 2) then raise "ERRR" end
                                        70})

    @aClass.set_method(:metodin, proc{@times += 1
      #if(@times == 2) then raise "ERRR" end
      "el valor es #{super()}"})

    @aClass.set_prototype(@anObject)

    #expect(@anObject.metodin).to eq(70)
    expect(@aClass.metodin).to eq("el valor es 70")
    #expect(@anObject.times).to eq(1)
    #expect(@aClass.times).to eq(1)
  end

  it 'should let use methods independently' do
    @anObject = Object.new
    @anObject.extend TP::Prototyped

    @aClass = Class.new
    @aClass.extend TP::Prototyped

    @anObject.set_prototype(@aClass)

    @anObject.set_method(:aMessage, proc{"Some message which says: #{self.anotherMessage}"})

    @aClass.set_method(:anotherMessage, proc{"I'm awesome"})

    expect(@anObject.aMessage).to eq("Some message which says: I'm awesome")

  end

end

