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

    @nuevoObjeto.define_singleton_method(:devolver, lambda{50})

    expect(@nuevoObjetoPrototipado.devolver).to eq(50)

  end

  it 'should redefine previous behaviour' do

    @nuevoObjetoPrototipado.define_singleton_method(:devolver, lambda{180})

    expect(@nuevoObjeto.devolver).to eq(50)
    expect(@nuevoObjetoPrototipado.devolver).to eq(180)

  end

end

