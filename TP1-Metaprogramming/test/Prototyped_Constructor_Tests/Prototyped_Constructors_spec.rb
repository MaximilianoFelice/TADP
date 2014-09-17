require 'rspec'
require "TP1/Metaprogramming"

describe 'Building Prototypes' do

  before(:all) do
    @guerrero = TP::PrototypedObject.new
    @guerrero.set_property(:energia, 0)
    @guerrero.set_property(:potencial_defensivo, 10)
    @guerrero.set_property(:potencial_ofensivo, 10)
  end

  it 'should instantiate correctly proc constructor' do
    Guerrero = TP::PrototypedConstructor.new(@guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    expect(Guerrero.builder.class).to eq(TP::ProcConstructor)
  end

  it 'should instantiate correctly hash constructor' do
    Guerrero = TP::PrototypedConstructor.new(@guerrero)

    expect(Guerrero.builder.class).to eq(TP::HashConstructor)
  end

  it 'should instantiate correctly copy constructor' do
    Guerrero = TP::PrototypedConstructor.copy(@guerrero)

    expect(Guerrero.builder.class).to eq(TP::CopyConstructor)
  end

  it 'should get right arity values from blocks' do

    Guerrero = TP::PrototypedConstructor.new(@guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    expect(Guerrero.arity).to eq(3)
  end

  it 'should get right arity values with hashes' do

    Guerrero = TP::PrototypedConstructor.new(@guerrero)

    expect(Guerrero.arity).to eq(1)
  end

  it 'should get right arity values with copies' do

    Guerrero = TP::PrototypedConstructor.copy(@guerrero)

    expect(Guerrero.arity).to eq(0)
  end

end