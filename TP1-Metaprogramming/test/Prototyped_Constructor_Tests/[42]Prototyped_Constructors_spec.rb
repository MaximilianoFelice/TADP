require 'rspec'
require "TP1/Metaprogramming"

describe 'Let Constructors build Prototypes' do

  before(:all) do
    @guerrero = TP::PrototypedObject.new
    @guerrero.set_property(:energia, 0)
    @guerrero.set_property(:potencial_defensivo, 0)
    @guerrero.set_property(:potencial_ofensivo, 0)
  end

  it 'should set a new constructor based on a prototype' do

    Guerrero = TP::ProtoConstructor.new(@guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    un_guerrero = Guerrero.new(100, 30, 10)
    expect(un_guerrero.energia).to eq(100)
    expect(un_guerrero.instance_module_variable_get(:energia)).to eq(100)
  end

  it 'should set a new constructor with a hash' do

    Guerrero = TP::ProtoConstructor.new(@guerrero)
    un_guerrero = Guerrero.new({energia: 100, potencial_ofensivo: 30, potencial_defensivo: 10})
    expect(un_guerrero.potencial_ofensivo).to eq(30)

  end

end