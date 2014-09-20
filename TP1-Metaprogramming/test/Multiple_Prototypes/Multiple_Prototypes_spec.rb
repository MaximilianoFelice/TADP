require 'rspec'
require "TP1/Metaprogramming"

describe 'Let objects have multiple prototypes' do

  before(:all) do
    @a_prototype = TP::PrototypedObject.new
    @another_prototype = TP::PrototypedObject.new
    @an_instance = TP::PrototypedObject.new
  end

  it 'let an object have multiple prototypes' do
    @a_prototype.methodin_ = proc{|value| value}
    @another_prototype.another_method_ = proc{"value"}

    @an_instance.set_prototypes(@a_prototype, @another_prototype)

    expect(@an_instance.methodin(50)).to eq(50)
    expect(@an_instance.another_method).to eq("value")
  end

  it 'should keep track even if prototypes change' do
    @another_prototype.one_more_method_ = proc{"foo"}

    expect(@an_instance.one_more_method).to eq("foo")
  end
end