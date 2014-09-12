require 'rspec'
require "TP1/Prototyped"
require "TP1/Metaprogramming"

describe '4.1 Programatic Prototypes' do

  before (:all) do
    @guerrero = TP::PrototypedObject.new
    @espadachin = TP::PrototypedObject.new
    @otro_guerrero
    @a = 50
  end

  it 'should set a new warrior' do
    @guerrero.set_property(:energia, 100)
    expect(@guerrero.energia).to eq(100)
    @guerrero.set_property(:potencial_defensivo, 10)
    @guerrero.set_property(:potencial_ofensivo, 30)
    @guerrero.set_method(:atacar_a,
                        proc {
                            |otro_guerrero|
                          if(otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
                            otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
                          end
                        });
    @guerrero.set_method(:recibe_danio, proc { |value| self.energia -= value})
    @otro_guerrero = @guerrero.clone
    @guerrero.atacar_a @otro_guerrero
    expect(@otro_guerrero.energia).to eq(80)
  end

  it 'should set a new prototyped object' do
    @espadachin.set_prototype(@guerrero)
    @espadachin.set_property(:habilidad, 0.5)
    @espadachin.set_property(:potencial_espada, 30)
    @espadachin.energia = 100

    @espadachin.set_property(:potencial_defensivo, 10)
    @espadachin.set_property(:potencial_ofensivo, 30)

    @espadachin.set_method(:potencial_ofensivo, proc {
      @potencial_ofensivo + self.potencial_espada * self.habilidad
      #should be  30    +       30             *       0.5    = 45
    })

    expect(@guerrero.respond_to?(:atacar_a)).to eq(true)
    expect(@espadachin.potencial_ofensivo).to eq(45)

    @otro_guerrero = @guerrero.clone
    expect(@otro_guerrero.potencial_defensivo).to eq(10)
    expect(@otro_guerrero.energia).to eq(100)
    @guerrero.atacar_a @otro_guerrero
    expect(@otro_guerrero.energia).to eq(80)

    @espadachin.atacar_a(@otro_guerrero)
    expect(@otro_guerrero.energia).to eq(45)
  end

  it 'prototyped objects can find behaviour in other instances' do
    @guerrero.set_method(:sanar, proc {
      self.energia = self.energia + 10
    })
    @espadachin.sanar
    expect(@espadachin.energia).to eq(110)
  end

  it 'should let prototyped objects redefine behaviour' do
    @guerrero.set_method(:potencial_ofensivo, proc {
      1000
    })
    expect(@espadachin.potencial_ofensivo).to eq(45)
  end

end